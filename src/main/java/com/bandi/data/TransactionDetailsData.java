package com.bandi.data;

import lombok.Data;

@Data
public class TransactionDetailsData {
	private Double amount;

	private String currency;

	private Long userId;
}