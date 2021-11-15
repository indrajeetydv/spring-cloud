package com.cs.pbs.os.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.pbs.os.api.common.TransactionRequest;
import com.cs.pbs.os.api.common.TransactionResponse;
import com.cs.pbs.os.api.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;

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
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {
        return service.saveOrder(request);
    }
}
