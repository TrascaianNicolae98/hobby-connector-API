package com.api.service;

import com.api.dto.SingUp;
import com.api.dto.UserLoginDto;
import com.api.entities.AppUser;
import com.api.entities.ConfirmationToken;
import com.api.error.AppException;
import com.api.repository.ConfirmationTokenRepository;
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
import org.springframework.web.servlet.ModelAndView;

@Service
@Log4j2
public class AuthenticationService {
  @Autowired private JacksonFactory jsonFactory;
  @Autowired private NetHttpTransport transport;
  @Autowired private AppUserService userService;
  @Autowired private ConnectionTypeService connectionTypeService;
  @Autowired private EmailService emailService;
  @Autowired private ConfirmationTokenRepository confirmationTokenRepository;

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
          AppUser appUser =
              new AppUser(
                  (String) payload.get("name"),
                  payload.getEmail(),
                  null,
                  "",
                  this.connectionTypeService.getByName("google"),
                  null);
          appUser.setEnable(true);
          this.userService.add(appUser);
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
    map.put("phoneNo", appUser.getPhoneNo());
    return map;
  }

  public String signUp(SingUp singUp) {
    if (!this.userService.userExist(singUp.getEmail())) {
      AppUser appUser =
          new AppUser(
              singUp.getFullName(),
              singUp.getEmail(),
              passwordEncoder(singUp.getPassword()),
              "",
              this.connectionTypeService.getByName("accout"),
              new ArrayList<>());
      this.userService.add(appUser);
      this.emailService.verifyConfirmationToken(appUser);
      return "Wainting for email confirmation!";
    } else throw new AppException("Email already used", HttpStatus.IM_USED);
  }

  public ModelAndView confirmEmaliToken(String confirmationToken) {
    ConfirmationToken token =
        confirmationTokenRepository.findConfirmationTokenByConfirmationToken(confirmationToken);

    if (token != null) {
      AppUser appUser = this.userService.getUserByEmail(token.getAppUser().getEmail());
      appUser.setEnable(true);
      this.userService.add(appUser);
      return new ModelAndView("redirect:" + "http://localhost:4200/login-email");
    }
    return null;
  }

  public String loginAccount(UserLoginDto userLoginDto) {
    boolean emailBoolean = this.userService.userExist(userLoginDto.getEmail());
    boolean passwordBool = false;
    boolean isEnable = false;
    if (emailBoolean == true) {
      isEnable = this.userService.getUserByEmail(userLoginDto.getEmail()).isEnable();
      passwordBool =
          passwordDecoder(
              userLoginDto.getPassword(),
              this.userService.getUserByEmail(userLoginDto.getEmail()).getPassword());
    }

    if (isEnable == false) {
      throw new AppException("Account inactive, verify your email!", HttpStatus.UNAUTHORIZED);
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
