package com.api.service;

import com.api.dto.GameDto;
import com.api.entities.Game;
import com.api.repository.GameRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
  @Autowired private GameRepository gameRepository;

  public Game save(Game game) {
    return this.gameRepository.save(game);
  }

  public void delete(Long id) {
    this.gameRepository.deleteById(id);
  }

  public List<Game> findAll() {
    return this.gameRepository.findAll();
  }

  public Game convertToEntity(GameDto gameDto) {
    return new Game(gameDto.getWinnerId(), gameDto.getDateTime(), gameDto.getPlace());
  }
}
