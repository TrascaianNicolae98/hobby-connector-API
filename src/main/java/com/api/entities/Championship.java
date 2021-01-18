package com.api.entities;

import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Championship {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  String name;
  String image1;
  String image2;

  @ManyToMany(
      cascade = {CascadeType.ALL},
      targetEntity = Team.class)
  @JoinTable(
      name = "championshipTeam",
      joinColumns = @JoinColumn(name = "team_id"),
      inverseJoinColumns = @JoinColumn(name = "championship_id"))
  private List<Team> teamList;

  public Championship(String name, String image1, String image2, List<Team> teamList) {
    this.name = name;
    this.image1 = image1;
    this.image2 = image2;
    this.teamList = teamList;
  }

  public void addTeam(Team team) {
    this.teamList.add(team);
  }
}
