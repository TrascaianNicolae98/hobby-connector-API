package com.api.dto;

import lombok.Data;

@Data
public class ReviewUpdateDto {
  private float stars;
  private String feedback;
}
