package com.api.service;

import com.api.dto.TeamDto;
import com.api.entities.Game;
import com.api.entities.Hobby;
import com.api.entities.Team;
import com.api.repository.TeamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
  @Autowired private TeamRepository teamRepository;
  @Autowired private GameService gameService;
  @Autowired private HobbyService hobbyService;

  public Team save(Team team) {
    return this.teamRepository.save(team);
  }

  public void delete(Long id) {
    this.teamRepository.deleteById(id);
  }

  public Team findById(Long id) { return this.teamRepository.findById(id).get(); }

  public List<Team> findAll() {
    return this.teamRepository.findAll();
  }

  public Team convertToEntity(TeamDto teamDto) {
    Game game = this.gameService.findById(teamDto.getGameId());
    Hobby hobby = this.hobbyService.findById(teamDto.getHobbyId());
    return new Team(teamDto.getType(), teamDto.getName(), game, hobby);
  }
}
