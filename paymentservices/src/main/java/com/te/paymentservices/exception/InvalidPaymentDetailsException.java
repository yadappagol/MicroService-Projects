package com.te.paymentservices.exception;

@SuppressWarnings("serial")
public class InvalidPaymentDetailsException extends RuntimeException {

	public InvalidPaymentDetailsException(String message) {
		super(message);
	}

}
