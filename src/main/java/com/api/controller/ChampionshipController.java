package com.api.controller;

import com.api.dto.ChampionshipTeamDto;
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
}
