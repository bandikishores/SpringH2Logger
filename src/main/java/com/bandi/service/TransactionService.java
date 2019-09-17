package com.bandi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bandi.converters.TransactionConverter;
import com.bandi.data.TransactionData;
import com.bandi.entity.Transaction;
import com.bandi.repository.TransactionRepository;
import com.bandi.validator.TransactionValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class TransactionService {

	private final TransactionRepository transactionRepository;

	private final TransactionConverter transactionConverter;

	private final TransactionValidator transactionValidator;

	@Transactional
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	@Transactional
	public List<Transaction> getAllTransactionsWithDetails() {
		return this.getAllTransactions();
	}

	@Transactional
	public Transaction getTransaction(long id) {
		return transactionRepository.getOne(id);
	}

	@Transactional
	public List<Transaction> getTransactionByUserName(String name) {
		return transactionRepository.findByUserName(name);
	}

	@Transactional
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Transactional
	public String createTransaction(TransactionData transactionData) {
		transactionValidator.validateTransaction(transactionData);
		Transaction transaction = transactionConverter.transform(transactionData);
		return saveTransaction(transaction).getId().toString();
	}
}
