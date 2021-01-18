package com.api.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class TemporaryTeam {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type;

  @ManyToOne(targetEntity = Hobby.class)
  @JoinColumn(name = "hobby_id")
  private Hobby hobby;

  private Long captainId;

  public TemporaryTeam(String type, Hobby hobby, Long captainId) {
    this.type = type;
    this.hobby = hobby;
    this.captainId = captainId;
  }
}
