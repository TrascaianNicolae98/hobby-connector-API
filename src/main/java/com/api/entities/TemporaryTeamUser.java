package com.api.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class TemporaryTeamUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(targetEntity = AppUser.class)
  @JoinColumn(name = "appUser_id")
  private AppUser appUser;

  @ManyToOne(targetEntity = TemporaryTeam.class)
  @JoinColumn(name = "temporaryTeam_id")
  private TemporaryTeam temporaryTeam;

  private int status;

  public TemporaryTeamUser(AppUser appUser, TemporaryTeam temporaryTeam, int status) {
    this.appUser = appUser;
    this.temporaryTeam = temporaryTeam;
    this.status = status;
  }
}
