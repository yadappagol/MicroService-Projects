package com.te.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	Order findByOrderId(int orderId);

}
