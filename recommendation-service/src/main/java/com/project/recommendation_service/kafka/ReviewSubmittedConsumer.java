package com.project.recommendation_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.project.recommendation_service.event.ReviewSubmittedEvent;
import com.project.recommendation_service.service.RecommendationService;

@Service
public class ReviewSubmittedConsumer {

	@Autowired
	private RecommendationService recommendationService;
	
	@KafkaListener(topics = "review-submitted", groupId = "recommendation-service")
	public void consume(ReviewSubmittedEvent event) {
		recommendationService.saveUserGenrePreference(event);
	}
}
