package com.project.recommendation_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.recommendation_service.entity.BookRecommendation;
import com.project.recommendation_service.service.RecommendationService;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
	
	@Autowired
	private RecommendationService recommendationService;

	@GetMapping("/books/{userId}")
	public ResponseEntity<List<BookRecommendation>> getRecommendations(@PathVariable Long userId){
		List<BookRecommendation> recommendedBooks = recommendationService.getRecommendedBooks(userId);
		 return ResponseEntity.ok(recommendedBooks);
				
	}
}
