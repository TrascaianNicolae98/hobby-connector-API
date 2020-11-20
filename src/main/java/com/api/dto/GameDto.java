package com.api.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class GameDto {
  private Long winnerId;
  private Date dateTime;
  private String place;

  public Long getWinnerId() {
    return winnerId;
  }

  public Date getDateTime() {
    return dateTime;
  }

  public String getPlace() {
    return place;
  }
}
