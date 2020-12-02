package com.api.controller;

import com.api.dto.GameDto;
import com.api.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class GameController {
  @Autowired private GameService gameService;

  @PostMapping("/games")
  public ResponseEntity saveGame(@RequestBody GameDto gameDto) {
    try {
      return new ResponseEntity(
          this.gameService.save(this.gameService.convertToEntity(gameDto)), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @GetMapping("/games")
  public ResponseEntity getGames() {
    try {
      return new ResponseEntity(this.gameService.findAll(), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PatchMapping("/games/{gameId}")
  public ResponseEntity deleteGame(@PathVariable Long gameId) {
    try {
      return new ResponseEntity(this.gameService.delete(gameId), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PatchMapping("/gamesPlace/{gameId}")
  public ResponseEntity setPlace(@PathVariable Long gameId, @RequestBody String place) {
    try {
      return new ResponseEntity(this.gameService.setPlace(place, gameId), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PatchMapping("/gamesDateTime/{gameId}")
  public ResponseEntity setDateTime(@PathVariable Long gameId, @RequestBody String dateTime) {
    try {
      return new ResponseEntity(this.gameService.setDateTime(dateTime, gameId), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PatchMapping("/gamesWinnerId/{gameId}")
  public ResponseEntity setWinnerId(@PathVariable Long gameId, @RequestBody Long winnerId) {
    try {
      return new ResponseEntity(this.gameService.setWinnerId(winnerId, gameId), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }
}
