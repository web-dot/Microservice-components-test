package com.project.rating_service.entity;

import java.time.LocalDateTime;

public class ReviewSubmittedEvent {

	private Long userId;
	private Long bookId;
	private Integer rating;
	private LocalDateTime timestamp;
	
	public ReviewSubmittedEvent() {
		
	}
	
	public ReviewSubmittedEvent(Long userId, Long bookId, Integer rating, LocalDateTime timestamp) {
		this.userId = userId;
		this.bookId = bookId;
		this.rating = rating;
		this.timestamp = timestamp;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
	
}
