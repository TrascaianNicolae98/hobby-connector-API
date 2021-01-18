package com.api.dto;

import java.util.ArrayList;
import lombok.Data;

@Data
public class TeamSentChamp {
  private String type;
  private String name;
  private Long gameId;
  private Long hobbyId;
  private ArrayList<Long> playersIds;
}
