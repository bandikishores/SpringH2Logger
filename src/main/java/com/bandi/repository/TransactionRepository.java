package com.bandi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bandi.entity.Transaction;

/**
 * Wraps the Underlying store which fetches and stores the User Information.
 * 
 * @author kibandi
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("SELECT u FROM Transaction u where u.createdUser.name = :name")
	public List<Transaction> findByUserName(@Param("name") String name);

}