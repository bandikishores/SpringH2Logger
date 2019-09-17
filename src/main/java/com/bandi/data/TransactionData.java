package com.bandi.data;

import java.util.List;

import com.bandi.entity.TransactionType;

import lombok.Data;

/**
 * UI Object which wraps the transaction
 * 
 * @author kibandi
 *
 */
@Data
public class TransactionData {

	private String transactionId;

	private Double totalAmount;

	private TransactionType totalType;

	private List<TransactionDetailsData> transactionDetails;

	private Long userId;
}
