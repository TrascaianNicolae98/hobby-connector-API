package com.api.service;

import com.api.entities.Hobby;
import com.api.entities.TemporaryTeam;
import com.api.repository.TemporaryTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemporaryTeamService {
  @Autowired TemporaryTeamRepository temporaryTeamRepository;

  public TemporaryTeam getTemporaryTeam(Long captainId, String type, Hobby hobby) {
    return this.temporaryTeamRepository.getByCaptainIdAndTypeAndHobby(captainId, type, hobby);
  }

  public void addTemporaryTeam(TemporaryTeam temporaryTeam) {
    this.temporaryTeamRepository.save(temporaryTeam);
  }
}
