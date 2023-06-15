package com.te.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.orderservice.common.Payment;
import com.te.orderservice.dto.TransactionRequest;
import com.te.orderservice.dto.TransactionResponse;
import com.te.orderservice.entity.Order;
import com.te.orderservice.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@RefreshScope
@Slf4j
@Transactional
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	String response;

	@Value("${microservice.payment-service.endpoints.endpoint.uri}")
	private String paymentEndPointUri;

	public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getOrderId());
        payment.setAmount(order.getPrice());
        log.info("Order-Service Request : "+new ObjectMapper().writeValueAsString(request));
        Payment paymentResponse = restTemplate.postForObject(paymentEndPointUri, payment, Payment.class);

        response = paymentResponse.getPaymentStatus().equalsIgnoreCase("success") ? "payment processing successful and order placed" : "there is a failure in payment api , order added to cart";
        log.info("Order Service getting Response from Payment-Service : "+new ObjectMapper().writeValueAsString(response));
        orderRepository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);

	}

	public Order getOrderDetails(int orderId) {
		return orderRepository.findByOrderId(orderId);
	}
}
