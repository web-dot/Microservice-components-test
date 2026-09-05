package com.project.rating_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookNotFinishedException extends RuntimeException {

	private final String message;
	private static final Logger LOGGER = LoggerFactory.getLogger(BookNotFinishedException.class);
	
	
	public BookNotFinishedException() {
		this.message = "Error Occured in BookNotFinishedException";
	}
	
	public BookNotFinishedException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
