package com.bandi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bandi.entity.Users;
import com.bandi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/*
 * Service which interacts and performs actions of Users.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class UserService {

	private final UserRepository userRepository;

	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	public Users getUser(long id) {
		return userRepository.getOne(id);
	}

	public Users getUserByName(String name) {
		return userRepository.findByName(name);
	}
}
