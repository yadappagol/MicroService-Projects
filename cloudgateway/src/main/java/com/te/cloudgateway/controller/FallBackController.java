package com.te.cloudgateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.cloudgateway.responsemessage.ResponseMessage;

import reactor.core.publisher.Mono;

@RestController
public class FallBackController {
	@RequestMapping("/orderFallBack")
	public ResponseEntity<ResponseMessage> orderServiceFallBack() {
		return new ResponseEntity<>(
				new ResponseMessage(true, "Server Unavailable To Respond ",
						"Order Service Taking Long Time To Respond Or Service Down ,Please try After Sometime"),
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	@RequestMapping("/paymentFallBack")
	public ResponseEntity<ResponseMessage> paymentServiceFallBack() {
		return new ResponseEntity<>(
				new ResponseMessage(true, "Server Unavailable To Respond ",
						"Payment Service Taking Long Time To Respond Or Service Down ,Please try After Sometime"),
				HttpStatus.SERVICE_UNAVAILABLE);
	}
}
