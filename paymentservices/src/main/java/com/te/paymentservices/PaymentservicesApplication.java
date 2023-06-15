package com.te.paymentservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient	
public class PaymentservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentservicesApplication.class, args);
	}

}
