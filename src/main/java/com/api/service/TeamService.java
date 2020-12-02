package com.api.service;

import com.api.dto.TeamDto;
import com.api.entities.Game;
import com.api.entities.Hobby;
import com.api.entities.Team;
import com.api.error.AppException;
import com.api.repository.TeamRepository;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TeamService {
  @Autowired private TeamRepository teamRepository;
  @Autowired private GameService gameService;
  @Autowired private HobbyService hobbyService;

  public Team save(Team team) {
    return this.teamRepository.save(team);
  }

  public Team delete(Long id) {
    Team team = this.findById(id);
    this.teamRepository.deleteById(id);
    return team;
  }

  public Team findById(Long id) {
    return this.teamRepository.findById(id).get();
  }

  public List<Team> findAll() {
    return this.teamRepository.findAll();
  }

  public Team convertToEntity(TeamDto teamDto) {
    Game game = this.gameService.findById(teamDto.getGameId());
    Hobby hobby = this.hobbyService.findById(teamDto.getHobbyId());
    return new Team(teamDto.getType(), teamDto.getName(), game, hobby);
  }

  public Team update(String name, Long id) {
    if (!this.teamRepository.existsById(id)) {
      log.error("Team with id" + id + "not found");
      throw new AppException("Team with id" + id + "not found", HttpStatus.NOT_FOUND);
    }
    Team team = this.teamRepository.findById(id).get();
    team.setName(name);
    this.teamRepository.save(team);
    return team;
  }
}
