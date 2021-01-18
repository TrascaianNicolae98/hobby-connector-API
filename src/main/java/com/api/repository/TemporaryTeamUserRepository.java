package com.api.repository;

import com.api.entities.TemporaryTeam;
import com.api.entities.TemporaryTeamUser;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemporaryTeamUserRepository extends JpaRepository<TemporaryTeamUser, Long> {
  public ArrayList<TemporaryTeamUser> getAllByTemporaryTeam(TemporaryTeam temporaryTeam);
}
