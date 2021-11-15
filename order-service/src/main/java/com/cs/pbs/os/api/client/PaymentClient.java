package com.cs.pbs.os.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs.pbs.os.api.common.Payment;


@FeignClient(name="PAYMENT-SERVICE")
@RequestMapping("/payment")
public interface PaymentClient {
	@PostMapping("/doPayment")
	public Payment doPayment(Payment payment);
}
