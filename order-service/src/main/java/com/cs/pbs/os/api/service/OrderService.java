package com.cs.pbs.os.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.cs.pbs.os.api.client.PaymentClient;
import com.cs.pbs.os.api.common.Payment;
import com.cs.pbs.os.api.common.TransactionRequest;
import com.cs.pbs.os.api.common.TransactionResponse;
import com.cs.pbs.os.api.entity.Order;
import com.cs.pbs.os.api.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RefreshScope
public class OrderService {
	Logger logger= LoggerFactory.getLogger(OrderService.class);
	@Autowired
	private OrderRepository repository;
	
	/*
	 * @Autowired
	 * 
	 * @Lazy private RestTemplate template;
	 */
	
	/*
	 * @Value("${microservice.payment-service.endpoints.endpoint.uri}") private
	 * String ENDPOINT_URL;
	 */
	
	@Autowired
	private PaymentClient client;
	
	/*
	 * public Order saveOrder(Order order) { return repository.save(order); }
	 */
	
	 public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
	        String response = "";
	        Order order = request.getOrder();
	        Payment payment = request.getPayment();
	        payment.setOrderId(order.getId());
	        payment.setAmount(order.getPrice());
	        //rest call
	        logger.info("Order-Service Request : "+new ObjectMapper().writeValueAsString(request));
	        //Payment paymentResponse = template.postForObject("http://localhost:9191/payment/doPayment", payment, Payment.class);
	        //Payment paymentResponse = template.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);
	        //Payment paymentResponse = template.postForObject(ENDPOINT_URL, payment, Payment.class);
	        //using feign client
	        Payment paymentResponse = client.doPayment(payment);
	        response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successful and order placed" : "there is a failure in payment api , order added to cart";
	        logger.info("Order Service getting Response from Payment-Service : "+new ObjectMapper().writeValueAsString(response));
	        repository.save(order);
	        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
	  }
}
