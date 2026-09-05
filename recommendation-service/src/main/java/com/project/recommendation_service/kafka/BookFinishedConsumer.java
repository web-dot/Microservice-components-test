package com.project.recommendation_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.project.recommendation_service.event.BookFinishedEvent;
import com.project.recommendation_service.service.RecommendationService;

@Service
public class BookFinishedConsumer {

	@Autowired
	private RecommendationService recommendationService;
	
	@KafkaListener(topics = "book-finished", groupId = "recommendation-service")
	public void consume(BookFinishedEvent event) {
		recommendationService.saveUserBookHistory(event);
	}
}
