package com.api.entities;

import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class ConfirmationToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String confirmationToken;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private AppUser appUser;

  public ConfirmationToken() {}

  public ConfirmationToken(AppUser appUser) {
    this.appUser = appUser;
    createdDate = new Date();
    confirmationToken = UUID.randomUUID().toString();
  }
}
