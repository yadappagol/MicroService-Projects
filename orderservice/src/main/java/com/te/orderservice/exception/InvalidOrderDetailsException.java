package com.te.orderservice.exception;

@SuppressWarnings("serial")
public class InvalidOrderDetailsException extends RuntimeException {

	public InvalidOrderDetailsException(String message) {
		super(message);
	}
}
