package com.project.reading_service.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.reading_service.entity.BookFinishedEvent;
import com.project.reading_service.entity.ReadingStatus;
import com.project.reading_service.entity.Status;
import com.project.reading_service.kafka.BookFinishedProducer;
import com.project.reading_service.repository.ReadingStatusRepository;

@Service
public class ReadingService {

	@Autowired
	private ReadingStatusRepository readingStatusRepo;
	
	@Autowired
	private BookFinishedProducer bookFinishedProducer;
	
	public ReadingStatus setStatus(Long userId, Long bookId, String statusString) {
		Status newStatus = Status.valueOf(statusString);
		ReadingStatus entity = readingStatusRepo.findByUserIdAndBookId(userId, bookId)
				.orElse(new ReadingStatus());
		
		entity.setUserId(userId);
		entity.setBookId(bookId);
		entity.setStatus(newStatus);
		
		ReadingStatus savedStatus = readingStatusRepo.save(entity);
		
		if(newStatus == Status.FINISHED) {
			BookFinishedEvent event = new BookFinishedEvent(userId, bookId, LocalDateTime.now());
			bookFinishedProducer.sendMessage(event);
		}
		return savedStatus;
		
	}
	
	
	public ReadingStatus getStatus(Long userId, Long bookId){
		Optional<ReadingStatus> statusOptional = readingStatusRepo.findByUserIdAndBookId(userId, bookId);
		return statusOptional.isPresent() ? statusOptional.get() : null;
	}
}
