package com.project.rating_service.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rating_service.dto.ReadingStatusResponse;
import com.project.rating_service.entity.RatingEntity;
import com.project.rating_service.entity.ReviewSubmittedEvent;
import com.project.rating_service.exception.BookNotFinishedException;
import com.project.rating_service.exception.DuplicateReviewException;
import com.project.rating_service.feignclient.ReadingServiceClient;
import com.project.rating_service.kafka.ReviewSubmittedProducer;
import com.project.rating_service.repository.RatingRepository;

import feign.FeignException;

@Service
public class RatingService {

	@Autowired
	private ReadingServiceClient readingServiceClient;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private ReviewSubmittedProducer reviewSubmittedProducer;
	
	public RatingEntity saveReview(Long userId, Long bookId, 
			Integer rating, String reviewText) {
	
		validateRating(rating);
		ReadingStatusResponse readingStatus = fetchReadingStatus(userId, bookId);
		
		if(!"FINISHED".equals(readingStatus.getStatus())) {
			throw new BookNotFinishedException();
		}
		
		ratingRepository.findByUserIdAndBookId(userId, bookId)
		.ifPresent(existing -> {
			throw new DuplicateReviewException("Book is already rated!");
		});
		
		RatingEntity review = new RatingEntity();
		review.setUserId(userId);
		review.setBookId(bookId);
		review.setRating(rating);
		review.setReviewText(reviewText);
		review.setCreatedAt(LocalDateTime.now());
		
		RatingEntity savedReview = ratingRepository.save(review);
		
		ReviewSubmittedEvent event = new ReviewSubmittedEvent(userId, bookId, rating, LocalDateTime.now());
		reviewSubmittedProducer.sendMessage(event);
		
		return savedReview;
	}
	
	private void validateRating(Integer rating) {
		if(rating == null || rating < 1 || rating > 5) {
			throw new IllegalArgumentException("Rating must be between 1 and 5");
		}
	}
	
	private ReadingStatusResponse fetchReadingStatus(Long userId, Long bookId) {
		try {
			ReadingStatusResponse readingStatus = readingServiceClient.getReadingStatus(userId, bookId);
			return readingStatus;
		}catch(FeignException e) {
			throw new RuntimeException("Failed to fetch reading status", e);
		}
	}

}
