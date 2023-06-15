package com.te.orderservice.controller;

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
import com.te.orderservice.dto.TransactionRequest;
import com.te.orderservice.dto.TransactionResponse;
import com.te.orderservice.entity.Order;
import com.te.orderservice.exception.InvalidOrderDetailsException;
import com.te.orderservice.responsemessage.ResponseMessage;
import com.te.orderservice.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/bookorders")
	public ResponseEntity<ResponseMessage> bookOrder(@RequestBody TransactionRequest request)
			throws JsonProcessingException {
		TransactionResponse saveOrder = orderService.saveOrder(request);
		log.info("order booking response from order Controller {}", saveOrder);
		return new ResponseEntity<>(new ResponseMessage(false, "Your Order booking Status was shown Below", saveOrder),
				HttpStatus.OK);
	}

	@GetMapping("/orderDetails/{orderId}")
	public ResponseEntity<ResponseMessage> getOrderDetails(@PathVariable int orderId) {
		Order orderDetails = orderService.getOrderDetails(orderId);
		if (orderDetails != null) {
			log.info("OrderDetails Fetched Successfully");
			return new ResponseEntity<>(new ResponseMessage(false, "Displaying Order Details", orderDetails),
					HttpStatus.OK);
		}
		log.info("OrderDetails Details Not Found");
		throw new InvalidOrderDetailsException("Order Deatils Not Found");
	}
}
