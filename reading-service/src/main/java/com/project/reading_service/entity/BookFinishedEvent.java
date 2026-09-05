package com.project.reading_service.entity;

import java.time.LocalDateTime;

public class BookFinishedEvent {

	private long userId;
	private long bookId;
	private LocalDateTime timestamp;
	
	public BookFinishedEvent() {
		
	}
	
	public BookFinishedEvent(Long userId, Long bookId, LocalDateTime timestamp) {
		this.userId = userId;
		this.bookId = bookId;
		this.timestamp = timestamp;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
