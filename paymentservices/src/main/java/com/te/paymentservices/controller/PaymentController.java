package com.te.paymentservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.te.paymentservices.entity.Payment;
import com.te.paymentservices.exception.InvalidPaymentDetailsException;
import com.te.paymentservices.response.ResponseMessage;
import com.te.paymentservices.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/dopayment")
	public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
		return paymentService.doPayment(payment);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<ResponseMessage> fetchPaymentHistoryByOrderId(@PathVariable int orderId)
			throws JsonProcessingException {
		Payment paymentDetails = paymentService.findPaymentHistoryByOrderId(orderId);
		if (paymentDetails != null) {
			log.info("Displaying Payment Details Of The Particular OrderId",paymentDetails);
			return new ResponseEntity<>(
					new ResponseMessage(false, "Displaying Payment Details Of The Particular OrderId", paymentDetails),
					HttpStatus.OK);
		}
		log.error("Payment Details Not Found For The Given Order");
		throw new InvalidPaymentDetailsException("Payment Details Not Found For The Given Order");
	}
}
