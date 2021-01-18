package com.api.dto;

import lombok.Data;

@Data
public class InvitePlayerDto {
  private String playerEmail;
  private Long hobbyId;
  private String teamType;
  private Long captainId;
}
