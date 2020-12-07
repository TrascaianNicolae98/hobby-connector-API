package com.api.controller;

import com.api.dto.JwtDto;
import com.api.dto.UserLoginDto;
import com.api.dto.UserSignUpDto;
import com.api.service.AuthenticationService;
import com.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AutentificationController {
  @Autowired AuthenticationService authenticationService;
  @Autowired private JwtUtil jwtUtil;

  @PostMapping("/loginWithGoogle")
  public ResponseEntity loginWithGoogle(@RequestBody String token) throws Exception {
    try {
      String jwt = this.authenticationService.createJwt(token);
      return new ResponseEntity(
          new JwtDto(jwt, this.jwtUtil.extractId(this.jwtUtil.extractAllClaims(jwt))),
          HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PostMapping("/signUp")
  public ResponseEntity signUp(@RequestBody UserSignUpDto userSignUpDto) {
    try {
      String jwt = this.authenticationService.signUp(userSignUpDto);
      return new ResponseEntity(
          new JwtDto(jwt, this.jwtUtil.extractId(this.jwtUtil.extractAllClaims(jwt))),
          HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PostMapping("/loginWithAccount")
  public ResponseEntity loginWithAccount(@RequestBody UserLoginDto userLoginDto) {
    try {
      String jwt = this.authenticationService.loginAccount(userLoginDto);
      return new ResponseEntity(
          new JwtDto(jwt, this.jwtUtil.extractId(this.jwtUtil.extractAllClaims(jwt))),
          HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }
}
