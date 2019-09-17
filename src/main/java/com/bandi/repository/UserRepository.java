package com.bandi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bandi.entity.Users;

/**
 * Wraps the Underlying store which fetches and stores the User Information.
 * 
 * @author kibandi
 *
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	@Query("SELECT u FROM Users u where u.name = :name")
	public Users findByName(@Param("name") String name);

}