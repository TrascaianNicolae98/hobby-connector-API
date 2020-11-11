package com.api.service;

import com.api.entities.Team;
import com.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
  @Autowired private TeamRepository teamRepository;

  public void add(Team team) {
    this.teamRepository.save(team);
  }
}
