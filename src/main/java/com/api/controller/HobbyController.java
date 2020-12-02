package com.api.controller;

import com.api.dto.HobbyDto;
import com.api.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class HobbyController {
  @Autowired
  private HobbyService hobbyService;

  @PostMapping("/hobbies")
  public ResponseEntity saveHobby(@RequestBody HobbyDto hobbyDto) {
    try {
      return new ResponseEntity(
              this.hobbyService.save(this.hobbyService.convertToEntity(hobbyDto)), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @GetMapping("/hobbies")
  public ResponseEntity getHobbies() {
    try {
      return new ResponseEntity(this.hobbyService.findAll(), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }
}
