package com.api.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type;
  private String name;

  @ManyToOne(targetEntity = Game.class)
  @JoinColumn(name = "game_id")
  private Game game;

  @ManyToOne(targetEntity = Hobby.class)
  @JoinColumn(name = "hobby_id")
  private Hobby hobby;

  @ManyToMany(targetEntity = Team.class)
  @JoinTable(
      name = "playerTeam",
      joinColumns = @JoinColumn(name = "player_id"),
      inverseJoinColumns = @JoinColumn(name = "team_id"))
  private List<AppUser> users;

  public Team(String type, String name, Game game, Hobby hobby) {
    this.type = type;
    this.name = name;
    this.game = game;
    this.hobby = hobby;
    this.users = new ArrayList<>();
  }

  public void addUser(AppUser appUser) {
    this.users.add(appUser);
  }
}
