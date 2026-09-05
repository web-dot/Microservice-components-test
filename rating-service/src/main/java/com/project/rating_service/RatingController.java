package com.project.rating_service;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.rating_service.entity.RatingEntity;
import com.project.rating_service.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/save")
	public ResponseEntity<RatingEntity> saveRatingForBook(@RequestParam Long userId,
			@RequestParam Long bookId, @RequestParam Integer rating, 
			@RequestParam String reviewText){
		RatingEntity result = ratingService.saveReview(userId, bookId, rating, reviewText);
		return ResponseEntity.status(HttpStatus.SC_CREATED).body(result);
	}
}
