package com.api.dto;

import com.api.entities.Team;
import java.util.ArrayList;
import lombok.Data;

@Data
public class ChampionshipDto {
  private Long id;
  private String name;
  private String image1;
  private String image2;
  private ArrayList<Team> teamList;
}
