package com.cs.pbs.os.api.common;

import com.cs.pbs.os.api.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TransactionResponse {
	private Order order;
    private Double amount;
    private String transactionId;
    private String message;
}
