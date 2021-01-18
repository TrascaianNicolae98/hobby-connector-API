package com.api.controller;

import com.api.dto.InvitePlayerDto;
import com.api.dto.TemporaryTeamResponse;
import com.api.service.TemporaryTeamUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TemporaryTeamController {
  private @Autowired TemporaryTeamUserService temporaryTeamUserService;

  @PostMapping("/teamporaryTeam")
  public ResponseEntity getTemporaryTeam(@RequestBody TemporaryTeamResponse temporaryTeamResponse) {
    try {
      return new ResponseEntity(
          this.temporaryTeamUserService.getTournametUsers(temporaryTeamResponse), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PostMapping("/temporaryTeamInvite")
  public ResponseEntity getTemporaryTeam(@RequestBody InvitePlayerDto invitePlayerDto) {
    try {
      return new ResponseEntity(
          this.temporaryTeamUserService.sendInvite(invitePlayerDto), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }
}
