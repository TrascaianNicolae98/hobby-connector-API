package com.api.dto;

import lombok.Data;

@Data
public class ReviewDto {
  private float stars;
  private String feedback;
  private Long userId;
}
