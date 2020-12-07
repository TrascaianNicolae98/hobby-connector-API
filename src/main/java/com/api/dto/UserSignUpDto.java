package com.api.dto;

import lombok.Data;

@Data
public class UserSignUpDto {
  private String fullName;
  private String email;
  private String password;
}
