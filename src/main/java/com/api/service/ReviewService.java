package com.api.service;

import com.api.dto.ReviewDto;
import com.api.dto.ReviewUpdateDto;
import com.api.entities.AppUser;
import com.api.entities.Review;
import com.api.error.AppException;
import com.api.repository.ReviewRepository;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ReviewService {
  @Autowired private ReviewRepository reviewRepository;
  @Autowired private AppUserService appUserService;

  public Review save(Review review) {
    return this.reviewRepository.save(review);
  }

  public Review delete(Long id) {
    Review review = this.findById(id);
    this.reviewRepository.deleteById(id);
    return review;
  }

  public Review findById(Long id) {
    return this.reviewRepository.findById(id).get();
  }

  public List<Review> findAll() {
    return this.reviewRepository.findAll();
  }

  public Review convertToEntity(ReviewDto reviewDto) {
    AppUser appUser = this.appUserService.findById(reviewDto.getUserId());
    return new Review(reviewDto.getStars(), reviewDto.getFeedback(), appUser);
  }

  public Review update(ReviewUpdateDto reviewUpdateDto, Long id) {
    if (!this.reviewRepository.existsById(id)) {
      log.error("Review with id" + id + "not found");
      throw new AppException("Review with id" + id + "not found", HttpStatus.NOT_FOUND);
    }
    Review review = this.reviewRepository.findById(id).get();
    review.setStars(reviewUpdateDto.getStars());
    review.setFeedback(reviewUpdateDto.getFeedback());
    this.reviewRepository.save(review);
    return review;
  }
}
