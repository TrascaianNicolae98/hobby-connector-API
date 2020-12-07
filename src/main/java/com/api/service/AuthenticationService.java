package com.api.service;

import com.api.dto.UserLoginDto;
import com.api.dto.UserSignUpDto;
import com.api.entities.AppUser;
import com.api.error.AppException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AuthenticationService {
  @Autowired private JacksonFactory jsonFactory;
  @Autowired private NetHttpTransport transport;
  @Autowired private AppUserService userService;
  @Autowired private ConnectionTypeService connectionTypeService;

  @Value("${googleClientId}")
  private String client_id;

  public GoogleIdToken tokenVerifier(String idTokenString)
      throws GeneralSecurityException, IOException {
    GoogleIdTokenVerifier verifier =
        new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            .setAudience(Collections.singletonList(client_id))
            .build();
    GoogleIdToken idToken = verifier.verify(idTokenString);
    return idToken;
  }

  public String createJwt(String token) throws Exception {
    try {
      GoogleIdToken idToken = this.tokenVerifier(token);
      if (idToken == null) {
        throw new AppException("Not a valid google connection!", HttpStatus.NOT_FOUND);
      } else {
        Payload payload = idToken.getPayload();
        if (!this.userService.userExist(payload.getEmail())) {
          this.userService.add(
              new AppUser(
                  (String) payload.get("name"),
                  payload.getEmail(),
                  null,
                  null,
                  this.connectionTypeService.getByName("google"),
                  null));
        }
        AppUser appUser = this.userService.getUserByEmail(payload.getEmail());
        Map<String, Object> map = this.getClaims(appUser);
        String jwt = this.userService.getJwtUtil().createToken(map, appUser.getFullName());
        return jwt;
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new AppException("Not a valid google connection!", HttpStatus.NOT_FOUND);
    }
  }

  public Map<String, Object> getClaims(AppUser appUser) {
    Map<String, Object> map = new HashMap<>();
    map.put("name", appUser.getFullName());
    map.put("email", appUser.getEmail());
    map.put("id", appUser.getId());
    return map;
  }

  public String signUp(UserSignUpDto userSignUpDto) {
    if (!this.userService.userExist(userSignUpDto.getEmail())) {
      AppUser appUser =
          new AppUser(
              userSignUpDto.getFullName(),
              userSignUpDto.getEmail(),
              passwordEncoder(userSignUpDto.getPassword()),
              null,
              this.connectionTypeService.getByName("accout"),
              new ArrayList<>());
      this.userService.add(appUser);

      Map<String, Object> map = this.getClaims(appUser);
      String jwt = this.userService.getJwtUtil().createToken(map, appUser.getFullName());
      return jwt;
    } else throw new AppException("Email already used", HttpStatus.IM_USED);
  }

  public String loginAccount(UserLoginDto userLoginDto) {
    boolean emailBoolean = this.userService.userExist(userLoginDto.getEmail());
    boolean passwordBool = false;
    if (emailBoolean == true) {
      passwordBool =
          passwordDecoder(
              userLoginDto.getPassword(),
              this.userService.getUserByEmail(userLoginDto.getEmail()).getPassword());
    }

    if (emailBoolean == false || passwordBool == false) {
      throw new AppException("Password or email incorect!", HttpStatus.NOT_FOUND);
    }

    return this.userService
        .getJwtUtil()
        .createToken(
            this.getClaims(this.userService.getUserByEmail(userLoginDto.getEmail())),
            this.userService.getUserByEmail(userLoginDto.getEmail()).getFullName());
  }

  public String passwordEncoder(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(password);
  }

  public boolean passwordDecoder(String password, String encodedPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.matches(password, encodedPassword);
  }
}
