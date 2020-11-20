package com.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Hobby {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  String name;
  String image1;
  String image2;
  String description;
  Integer nrOfPlayers;

  public Hobby(String name, String image1, String image2, String description, Integer nrOfPlayers) {
    this.name = name;
    this.image1 = image1;
    this.image2 = image2;
    this.description = description;
    this.nrOfPlayers = nrOfPlayers;
  }
}
