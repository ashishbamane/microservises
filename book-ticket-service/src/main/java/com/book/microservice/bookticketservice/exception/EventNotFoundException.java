package com.book.microservice.bookticketservice.exception;

import org.springframework.http.HttpStatus;

public class EventNotFoundException extends RuntimeException {
	String message;
	HttpStatus httpStatus;
	public EventNotFoundException(String message, HttpStatus httpStatus) {
		super(message);
	}
}
