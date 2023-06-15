package com.te.paymentservices.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.paymentservices.entity.Payment;
import com.te.paymentservices.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	public Payment doPayment(Payment payment) throws JsonProcessingException {
		log.info("Payment Service Request: {}", new ObjectMapper().writeValueAsString(payment));
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());

		Payment paymentStatus = findPaymentHistoryByOrderId(payment.getOrderId());
		if (paymentStatus != null && paymentStatus.getOrderId() == payment.getOrderId()) {
			paymentStatus.setPaymentId(paymentStatus.getPaymentId());
			paymentStatus.setOrderId(payment.getOrderId());
			paymentStatus.setAmount(payment.getAmount());
			paymentStatus.setPaymentStatus(payment.getPaymentStatus());
			paymentStatus.setTransactionId(payment.getTransactionId());
			return paymentRepository.save(paymentStatus);
		}

		return paymentRepository.save(payment);
	}

	public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
		Payment payment = paymentRepository.findByOrderId(orderId);
		log.info("Payment Service findPaymentHistoryByOrderId: {}", new ObjectMapper().writeValueAsString(payment));
		return payment;
	}

	public String paymentProcessing() {
		return new Random().nextBoolean() ? "success" : "failed";
	}

}
