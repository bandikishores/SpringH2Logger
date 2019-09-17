package com.bandi.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * The Transaction Details Entity Representing the Data from DB
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTION_DETAILS")
public class TransactionDetails {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaction_id")
	private Transaction transaction;

	@JoinColumn(name = "user_id", unique = false, nullable = false)
	@OneToOne
	private Users user;

	@JoinColumn(name = "modified_user_id", unique = false, nullable = true)
	@OneToOne
	private Users modifiedUser;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "currency", nullable = false)
	private String currency = "INR";

	@Column(name = "createdOn", nullable = false)
	private LocalDateTime createdOn = LocalDateTime.now();

	@Column(name = "modifiedOn", nullable = true)
	private LocalDateTime modifiedOn = LocalDateTime.now();
}
