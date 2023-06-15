package com.te.paymentservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.paymentservices.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	Payment findByOrderId(int orderId);

}
