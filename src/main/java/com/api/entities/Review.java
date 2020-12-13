package com.api.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  float stars;
  String feedback;
  Long userId;

  @ManyToOne(targetEntity = AppUser.class)
  @JoinColumn(name = "appUser_id")
  private AppUser appUser;

  public Review(float stars, String feedback, AppUser appUser) {
    this.stars = stars;
    this.feedback = feedback;
    this.appUser = appUser;
  }
}
