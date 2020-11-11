package com.api.config;

import com.api.entities.ConnectionType;
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
    ConnectionType connectionType1 = new ConnectionType("google");
    ConnectionType connectionType2 = new ConnectionType("account");
    connectionTypeRepository.save(connectionType1);
    connectionTypeRepository.save(connectionType2);
  }
}
