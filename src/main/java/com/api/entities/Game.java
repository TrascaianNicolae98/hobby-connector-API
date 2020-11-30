package com.api.entities;

import java.sql.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long winnerId;
  private Date dateTime;
  private String place;

  public Game(Long winnerId, Date dateTime, String place) {
    this.winnerId = winnerId;
    this.dateTime = dateTime;
    this.place = place;
  }
}
