package com.api.service;

import com.api.entities.AppUser;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        throw new Exception("Not a valid google connection!");
      } else {
        Payload payload = idToken.getPayload();
        if (!this.userService.userExist(payload.getEmail())) {
          this.userService.add(
              new AppUser(
                  (String) payload.get("name"),
                  payload.getEmail(),
                  null,
                  (String) payload.get("phoneNo"),
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
      throw e;
    }
  }

  public Map<String, Object> getClaims(AppUser appUser) {
    Map<String, Object> map = new HashMap<>();
    map.put("name", appUser.getFullName());
    map.put("email", appUser.getEmail());
    map.put("id", appUser.getId());
    return map;
  }
}
