package com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
  private Long id;
  private String fullname;
  private String phoneNo;
  private String email;
  private String jwt;
}
