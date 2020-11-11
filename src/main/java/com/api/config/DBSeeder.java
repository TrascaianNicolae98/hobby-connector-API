package com.api.config;

import com.api.entities.AppUser;
import com.api.entities.ConnectionType;
import com.api.entities.Team;
import com.api.repository.AppUserRepository;
import com.api.repository.ConnectionTypeRepository;
import com.api.repository.TeamRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBSeeder {
  @Autowired private AppUserRepository appUserRepository;
  @Autowired private ConnectionTypeRepository connectionTypeRepository;
  @Autowired private TeamRepository teamRepository;

  public DBSeeder() {}

  @PostConstruct
  public void seed() {
    ConnectionType connectionTye = new ConnectionType("a");
    Team team = new Team("a", "b");
    AppUser appUser = new AppUser("a", "b", "c", "d", connectionTye, team);
    connectionTypeRepository.save(connectionTye);
    teamRepository.save(team);
    appUserRepository.save(appUser);
  }
}
