package com.api.config;

import com.api.entities.Championship;
import com.api.entities.ConnectionType;
import com.api.entities.Hobby;
import com.api.repository.ChampionshipRepository;
import com.api.repository.ConnectionTypeRepository;
import com.api.repository.HobbyRepository;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBSeeder {
  @Autowired private ConnectionTypeRepository connectionTypeRepository;
  @Autowired private HobbyRepository hobbyRepository;
  @Autowired private ChampionshipRepository championshipRepository;

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
        new Hobby(
            "Football",
            "https://images.unsplash.com/flagged/photo-1571771710019-ca58cf80f225?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80",
            "link2",
            "descriere",
            6);
    Hobby hobby2 =
        new Hobby(
            "Basket",
            "https://images.unsplash.com/photo-1467809941367-bbf259d44dd6?ixlib=rb-1.2.1&auto=format&fit=crop&w=1190&q=80",
            "link2",
            "descriere",
            5);
    Hobby hobby3 =
        new Hobby(
            "Paintball",
            "https://images.unsplash.com/photo-1522499820455-9c821da82b9e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80",
            "link2",
            "descriere",
            4);
    Hobby hobby4 =
        new Hobby(
            "Handball",
            "https://www.athens2020.org/sites/default/files/styles/fullhd/public/disciplines-handball-ball.jpg?itok=wtwhHVEJ",
            "link2",
            "descriere",
            3);
    Hobby hobby5 =
        new Hobby(
            "Chess",
            "https://images.unsplash.com/photo-1523875194681-bedd468c58bf?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",
            "link2",
            "descriere",
            3);
    Hobby hobby7 =
        new Hobby(
            "Volleyball",
            "https://images.unsplash.com/photo-1562552052-c72ceddf93dc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80",
            "link2",
            "descriere",
            3);
    this.hobbyRepository.save(hobby1);
    this.hobbyRepository.save(hobby2);
    this.hobbyRepository.save(hobby3);
    this.hobbyRepository.save(hobby4);
    this.hobbyRepository.save(hobby5);
    this.hobbyRepository.save(hobby7);
  }

  @PostConstruct
  public void seedChampionships() {
    Championship championship1 =
        new Championship(
            "Football",
            "https://images.unsplash.com/flagged/photo-1571771710019-ca58cf80f225?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80",
            "https://images.unsplash.com/flagged/photo-1571771710019-ca58cf80f225?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80",
            new ArrayList<>());

    Championship championship2 =
        new Championship(
            "Basket",
            "https://images.unsplash.com/photo-1467809941367-bbf259d44dd6?ixlib=rb-1.2.1&auto=format&fit=crop&w=1190&q=80",
            "https://images.unsplash.com/photo-1467809941367-bbf259d44dd6?ixlib=rb-1.2.1&auto=format&fit=crop&w=1190&q=80",
            new ArrayList<>());

    Championship championship3 =
        new Championship(
            "Paintball",
            "https://images.unsplash.com/photo-1522499820455-9c821da82b9e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80",
            "link2",
            new ArrayList<>());

    Championship championship4 =
        new Championship(
            "Handball",
            "https://www.athens2020.org/sites/default/files/styles/fullhd/public/disciplines-handball-ball.jpg?itok=wtwhHVEJ",
            "link2",
            new ArrayList<>());

    Championship championship5 =
        new Championship(
            "Chess",
            "https://images.unsplash.com/photo-1523875194681-bedd468c58bf?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",
            "link2",
            new ArrayList<>());

    Championship championship6 =
        new Championship(
            "Volleyball",
            "https://images.unsplash.com/photo-1562552052-c72ceddf93dc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80",
            "link2",
            new ArrayList<>());
    this.championshipRepository.save(championship1);
    this.championshipRepository.save(championship2);
    this.championshipRepository.save(championship3);
    this.championshipRepository.save(championship4);
    this.championshipRepository.save(championship5);
    this.championshipRepository.save(championship6);
  }
}
