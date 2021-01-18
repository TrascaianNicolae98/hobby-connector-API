package com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TemporaryTeamUserDto {
  private Long id;
  private String fullname;
  private String phoneNo;
  private String email;
}
