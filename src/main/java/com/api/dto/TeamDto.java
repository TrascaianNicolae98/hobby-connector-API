package com.api.dto;

import lombok.Data;

@Data
public class TeamDto {
  private String type;
  private String name;
  private Long gameId;
  private Long hobbyId;
}
