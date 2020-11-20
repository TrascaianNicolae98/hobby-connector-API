package com.api.config;

import com.api.entities.ConnectionType;
import com.api.entities.Hobby;
import com.api.repository.ConnectionTypeRepository;
import com.api.repository.HobbyRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBSeeder {
  @Autowired private ConnectionTypeRepository connectionTypeRepository;
  @Autowired private HobbyRepository hobbyRepository;

  public DBSeeder() {}

  @PostConstruct
  public void seedConnection() {
    ConnectionType connectionType1 = new ConnectionType("google");
    ConnectionType connectionType2 = new ConnectionType("account");
    connectionTypeRepository.save(connectionType1);
    connectionTypeRepository.save(connectionType2);
  }

  @PostConstruct
  public void seedHobies() {
    Hobby hobby1 =
        new Hobby("hobby2", "https://wallpaperaccess.com/full/563319.jpg", "link2", "descriere", 6);
    Hobby hobby2 =
        new Hobby("hobby3", "https://wallpaperaccess.com/full/563319.jpg", "link2", "descriere", 5);
    Hobby hobby3 =
        new Hobby("hobby4", "https://wallpaperaccess.com/full/563319.jpg", "link2", "descriere", 4);
    Hobby hobby4 =
        new Hobby("hobby5", "https://wallpaperaccess.com/full/563319.jpg", "link2", "descriere", 3);
    Hobby hobby5 =
        new Hobby("hobby6", "https://wallpaperaccess.com/full/563319.jpg", "link2", "descriere", 3);
    Hobby hobby7 =
        new Hobby("hobby7", "https://wallpaperaccess.com/full/563319.jpg", "link2", "descriere", 3);
    this.hobbyRepository.save(hobby1);
    this.hobbyRepository.save(hobby2);
    this.hobbyRepository.save(hobby3);
    this.hobbyRepository.save(hobby4);
    this.hobbyRepository.save(hobby5);
    this.hobbyRepository.save(hobby7);
  }
}
