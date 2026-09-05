package com.project.rating_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicateReviewException extends RuntimeException {

	private final String message;
	private static final Logger LOGGER = LoggerFactory.getLogger(BookNotFinishedException.class);
	
	
	public DuplicateReviewException() {
		this.message = "Error Occured in DuplicateReviewException";
	}
	
	public DuplicateReviewException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
