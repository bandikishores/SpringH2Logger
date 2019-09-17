package com.bandi.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The Transaction Entity Representing the Data from DB
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction", fetch = FetchType.EAGER, orphanRemoval = true)
	private List<TransactionDetails> details;

	@Enumerated(EnumType.STRING)
	@Column
	private TransactionType transactionType;

	@Column
	private Double totalAmount;

	@JoinColumn(name = "user_id", unique = false, nullable = false)
	@OneToOne
	private Users createdUser;

	@Column(name = "createdOn", nullable = false)
	private LocalDateTime createdOn = LocalDateTime.now();

	@Column(name = "modifiedOn", nullable = true)
	private LocalDateTime modifiedOn = LocalDateTime.now();
	
	public void addTransactionDetails(TransactionDetails transactionDetails) {
		if (details == null) {
			details = new ArrayList<>();
		}
		transactionDetails.setTransaction(this);
		details.add(transactionDetails);
	}
}
