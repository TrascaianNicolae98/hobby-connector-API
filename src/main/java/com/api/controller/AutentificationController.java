package com.api.controller;

import com.api.dto.LoginResponse;
import com.api.dto.SingUp;
import com.api.dto.UserLoginDto;
import com.api.repository.ConfirmationTokenRepository;
import com.api.service.AuthenticationService;
import com.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AutentificationController {
  @Autowired AuthenticationService authenticationService;
  @Autowired private JwtUtil jwtUtil;
  @Autowired private ConfirmationTokenRepository confirmationTokenRepository;

  @PostMapping("/loginWithGoogle")
  public ResponseEntity loginWithGoogle(@RequestBody String token) throws Exception {
    try {
      String jwt = this.authenticationService.createJwt(token);
      Long id = jwtUtil.extractId(jwtUtil.extractAllClaims(jwt));
      String fullname = jwtUtil.extractUsername(jwtUtil.extractAllClaims(jwt));
      String phoneNo = jwtUtil.extractPhoneNo(jwtUtil.extractAllClaims(jwt));
      String email = jwtUtil.extractEmail(jwtUtil.extractAllClaims(jwt));
      return new ResponseEntity(
          new LoginResponse(id, fullname, phoneNo, email, jwt), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PostMapping("/signUp")
  public ResponseEntity signUp(@RequestBody SingUp singUp) {
    try {
      String message = this.authenticationService.signUp(singUp);
      return new ResponseEntity(message, HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PostMapping("/loginWithAccount")
  public ResponseEntity loginWithAccount(@RequestBody UserLoginDto userLoginDto) {
    try {
      String jwt = this.authenticationService.loginAccount(userLoginDto);
      Long id = jwtUtil.extractId(jwtUtil.extractAllClaims(jwt));
      String fullname = jwtUtil.extractUsername(jwtUtil.extractAllClaims(jwt));
      String phoneNo = jwtUtil.extractPhoneNo(jwtUtil.extractAllClaims(jwt));
      String email = jwtUtil.extractEmail(jwtUtil.extractAllClaims(jwt));

      return new ResponseEntity(
          new LoginResponse(id, fullname, phoneNo, email, jwt), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @GetMapping(value = "/confirm-account")
  public ModelAndView confirmUserAccount(@RequestParam("token") String confirmationToken) {
    return this.authenticationService.confirmEmaliToken(confirmationToken);
  }
}
