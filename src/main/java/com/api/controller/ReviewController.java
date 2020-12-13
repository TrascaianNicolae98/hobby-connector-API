package com.api.controller;

import com.api.dto.ReviewDto;
import com.api.dto.ReviewUpdateDto;
import com.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ReviewController {
  @Autowired private ReviewService reviewService;

  @PostMapping("/reviews")
  public ResponseEntity saveReview(@RequestBody ReviewDto reviewDto) {
    try {
      return new ResponseEntity(
          this.reviewService.save(this.reviewService.convertToEntity(reviewDto)), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @GetMapping("/reviews")
  public ResponseEntity getReviews() {
    try {
      return new ResponseEntity(this.reviewService.findAll(), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PatchMapping("/reviews/{reviewId}")
  public ResponseEntity deleteReviews(@PathVariable Long reviewId) {
    try {
      return new ResponseEntity(this.reviewService.delete(reviewId), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PatchMapping("/reviewsUP/{reviewId}")
  public ResponseEntity updateReview(
      @PathVariable Long reviewId, @RequestBody ReviewUpdateDto reviewUpdateDto) {
    try {
      return new ResponseEntity(
          this.reviewService.update(reviewUpdateDto, reviewId), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }
}
