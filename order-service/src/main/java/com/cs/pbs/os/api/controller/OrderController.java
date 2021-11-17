package com.cs.pbs.os.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.pbs.os.api.common.TransactionRequest;
import com.cs.pbs.os.api.common.TransactionResponse;
import com.cs.pbs.os.api.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService service; 
	
	/*
	 * @PostMapping("/bookOrder") public Order bookOrder(@RequestBody Order order) {
	 * return service.saveOrder(order); }
	 */
	
	@PostMapping("/bookOrder")
	@CircuitBreaker(name = "mainservice", fallbackMethod="testFallBack")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {
        return service.saveOrder(request);
    }
	
	private  TransactionResponse testFallBack(Exception e){
		TransactionResponse resp=new TransactionResponse();
		resp.setMessage("PaymentService is down, please try after some time");
	    //return new ResponseEntity<String>("In fallback method", HttpStatus.INTERNAL_SERVER_ERROR);
		return resp;
	}
}
