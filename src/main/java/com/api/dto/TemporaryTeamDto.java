package com.api.dto;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TemporaryTeamDto {
  private ArrayList<TemporaryTeamUserDto> amicalUsers;
  private ArrayList<TemporaryTeamUserDto> championshipUsers;
}
