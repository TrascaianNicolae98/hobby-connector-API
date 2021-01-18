package com.api.controller;

import com.api.dto.ChampionshipTeamDto;
import com.api.dto.TeamSentChamp;
import com.api.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ChampionshipController {
  @Autowired private ChampionshipService championshipService;

  @PostMapping("/championships")
  public ResponseEntity saveChampionship(@RequestBody ChampionshipTeamDto championshipTeamDto) {
    try {
      return new ResponseEntity(this.championshipService.save(championshipTeamDto), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @GetMapping("/championships")
  public ResponseEntity getChampionship() {
    try {
      return new ResponseEntity(this.championshipService.findAll(), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PostMapping("/saveTeamChampionship")
  public ResponseEntity saveTeamChampionship(@RequestBody TeamSentChamp teamSentChamp) {
    try {
      return new ResponseEntity(
          this.championshipService.saveTeamChampionship(teamSentChamp), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PostMapping("/teamsByChampionshipId")
  public ResponseEntity teamsByChampionshipId(@RequestBody Long champId) {
    try {
      return new ResponseEntity(this.championshipService.getChampTeams(champId), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }
}
