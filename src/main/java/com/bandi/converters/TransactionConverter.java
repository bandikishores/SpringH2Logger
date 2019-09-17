package com.bandi.converters;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bandi.data.TransactionData;
import com.bandi.data.TransactionDetailsData;
import com.bandi.entity.Transaction;
import com.bandi.entity.TransactionDetails;
import com.bandi.entity.Users;
import com.bandi.exception.SplitWiseException;
import com.bandi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class TransactionConverter {

	private final UserRepository userRepository;

	public Transaction transform(TransactionData transactionData) {
		Transaction transaction = new Transaction();
		LocalDateTime localDateTime = LocalDateTime.now();
		transaction.setTotalAmount(transactionData.getTotalAmount());
		transaction.setTransactionType(transactionData.getTotalType());
		transaction.setCreatedOn(localDateTime);
		Optional<Users> user = userRepository.findById(transactionData.getUserId());
		transaction.setCreatedUser(user.get());

		for (TransactionDetailsData data : transactionData.getTransactionDetails()) {
			TransactionDetails transactionDetail = new TransactionDetails();
			transactionDetail.setAmount(data.getAmount());
			transactionDetail.setCreatedOn(localDateTime);
			if (data.getCurrency() != null) {
				transactionDetail.setCurrency(data.getCurrency());
			}
			user = userRepository.findById(data.getUserId());
			if (!user.isPresent()) {
				throw new SplitWiseException("User not found " + data.getUserId());
			}
			transactionDetail.setUser(user.get());
			transaction.addTransactionDetails(transactionDetail);
		}

		return transaction;
	}
}
