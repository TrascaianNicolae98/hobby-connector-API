package com.api.service;

import com.api.dto.TeamDto;
import com.api.entities.Team;
import com.api.repository.TeamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
  @Autowired private TeamRepository teamRepository;

  public Team save(Team team) {
    return this.teamRepository.save(team);
  }

  public void delete(Long id) {
    this.teamRepository.deleteById(id);
  }

  public List<Team> findAll() {
    return this.teamRepository.findAll();
  }

  public Team convertToEntity(TeamDto teamDto) {
    return new Team(teamDto.getType(), teamDto.getName());
  }
}
