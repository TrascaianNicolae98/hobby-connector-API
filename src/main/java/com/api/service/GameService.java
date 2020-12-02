package com.api.service;

import com.api.dto.GameDto;
import com.api.entities.Game;
import com.api.error.AppException;
import com.api.repository.GameRepository;
import java.sql.Date;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class GameService {
  @Autowired private GameRepository gameRepository;

  public Game save(Game game) {
    return this.gameRepository.save(game);
  }

  public Game delete(Long id) {
    Game game = this.gameRepository.findById(id).get();
    this.gameRepository.deleteById(id);
    return game;
  }

  public List<Game> findAll() {
    return this.gameRepository.findAll();
  }

  public Game findById(Long id) {
    return this.gameRepository.findById(id).get();
  }

  public Game convertToEntity(GameDto gameDto) {
    return new Game(gameDto.getWinnerId(), gameDto.getDateTime(), gameDto.getPlace());
  }

  public Game updateGame(GameDto gameDto, Long id) {
    if (!this.gameRepository.existsById(id)) {
      log.error("Game with id " + id + " not found");
      throw new AppException("Game with id " + id + " not found", HttpStatus.NOT_FOUND);
    }
    Game game = this.gameRepository.findById(id).get();
    game.setDateTime(gameDto.getDateTime());
    game.setPlace(gameDto.getPlace());
    game.setWinnerId(gameDto.getWinnerId());
    this.gameRepository.save(game);
    return game;
  }

  public Game setPlace(String place, Long id) {
    if (!this.gameRepository.existsById(id)) {
      log.error("Game with id " + id + " not found");
      throw new AppException("Game with id " + id + " not found", HttpStatus.NOT_FOUND);
    }
    Game game = this.gameRepository.findById(id).get();
    game.setPlace(place);
    this.gameRepository.save(game);
    return game;
  }

  public Game setDateTime(String dateTime, Long id) {
    if (!this.gameRepository.existsById(id)) {
      log.error("Game with id " + id + " not found");
      throw new AppException("Game with id " + id + " not found", HttpStatus.NOT_FOUND);
    }
    Game game = this.gameRepository.findById(id).get();
    game.setDateTime(Date.valueOf(dateTime));
    this.gameRepository.save(game);
    return game;
  }

  public Game setWinnerId(Long winnerId, Long id) {
    if (!this.gameRepository.existsById(id)) {
      log.error("Game with id " + id + " not found");
      throw new AppException("Game with id " + id + " not found", HttpStatus.NOT_FOUND);
    }
    Game game = this.gameRepository.findById(id).get();
    game.setWinnerId(winnerId);
    this.gameRepository.save(game);
    return game;
  }
}
