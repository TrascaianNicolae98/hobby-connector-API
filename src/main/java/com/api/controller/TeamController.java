package com.api.controller;

import com.api.dto.TeamDto;
import com.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TeamController {
  @Autowired private TeamService teamService;

  @PostMapping("/teams")
  public ResponseEntity saveTeam(@RequestBody TeamDto teamDto) {
    try {
      return new ResponseEntity(
          this.teamService.save(this.teamService.convertToEntity(teamDto)), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @GetMapping("/teams")
  public ResponseEntity getTeams() {
    try {
      return new ResponseEntity(this.teamService.findAll(), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }
}
