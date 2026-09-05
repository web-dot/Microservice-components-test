package com.project.rating_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.project.rating_service.entity.ReviewSubmittedEvent;

@Service
public class ReviewSubmittedProducer {
	
	private final KafkaTemplate<String, ReviewSubmittedEvent> kafkaTemplate;
	
	public ReviewSubmittedProducer(KafkaTemplate<String, ReviewSubmittedEvent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	
	public void sendMessage(ReviewSubmittedEvent event) {
		Message<ReviewSubmittedEvent> message = MessageBuilder
		.withPayload(event)
		.setHeader(KafkaHeaders.TOPIC, "review-submitted")
		.build();
		kafkaTemplate.send(message);
		
	}
}
