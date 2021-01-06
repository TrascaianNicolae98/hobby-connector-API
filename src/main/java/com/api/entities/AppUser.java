package com.api.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class AppUser implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fullName;
  private String email;
  private String password;
  private String phoneNo;

  @ManyToOne(targetEntity = ConnectionType.class)
  @JoinColumn(name = "connection_id")
  private ConnectionType connectionType;

  @ManyToMany(targetEntity = Team.class)
  @JoinTable(
      name = "playerTeam",
      joinColumns = @JoinColumn(name = "player_id"),
      inverseJoinColumns = @JoinColumn(name = "team_id"))
  private List<Team> teamList;

  private boolean isEnable;

  public AppUser(
      String fullName,
      String email,
      String password,
      String phoneNo,
      ConnectionType connectionType,
      List<Team> teamList) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.phoneNo = phoneNo;
    this.connectionType = connectionType;
    this.teamList = teamList;
    this.isEnable = false;
  }
}
