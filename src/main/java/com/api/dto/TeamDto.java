package com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamDto {
  private String type;
  private String name;
  private Long gameId;
  private Long hobbyId;
}
