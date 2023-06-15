package com.te.paymentservices.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "payment_details")
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	@Id
	@SequenceGenerator(name = "payment_sequence_generatoor", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "payment_sequence_generatoor")
	private int paymentId;
	private String paymentStatus;
	private String transactionId;
	private int orderId;
	private int amount;

}
