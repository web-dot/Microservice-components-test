package com.project.reading_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.project.reading_service.entity.BookFinishedEvent;

@Service
public class BookFinishedProducer {

	KafkaTemplate<String, BookFinishedEvent> kafkaTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(BookFinishedEvent.class);
	
	public BookFinishedProducer(KafkaTemplate<String, BookFinishedEvent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(BookFinishedEvent data) {
	
//		LOGGER.info(String.format("Message sent -> %d", data.toString()));
		
		Message<BookFinishedEvent> message = MessageBuilder
				.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC, "book-finished")
				.build();
		kafkaTemplate.send(message);
	}
}
