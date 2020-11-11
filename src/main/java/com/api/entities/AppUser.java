package com.api.entities;

import java.io.Serializable;
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

  @ManyToOne(targetEntity = Team.class)
  @JoinColumn(name = "team_id")
  private Team team;

  public AppUser(
      String fullName,
      String email,
      String password,
      String phoneNo,
      ConnectionType connectionType,
      Team team) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.phoneNo = phoneNo;
    this.connectionType = connectionType;
    this.team = team;
  }
}
