package com.sana.apple.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;

import com.sana.apple.enums.StatusEnum;
import com.sana.apple.model.User;


public interface IUserService {

	/**
	 * Save user info in database
	 * 
	 * @return - returns User object
	 */
	Optional<User> addUser(User user);

	Optional<User> updateUser(User user);

	//Optional<List<User>> getUsers();
	
	Optional<User> getUser(Integer id);

	Optional<User> getUser(String mobileNumber);

	Optional<User> deleteUser(User user);

	Optional<Authentication> loginUser(User user);

	Optional<User> getUserByMobileNumber(String mobileNumber);

	Optional<List<User>> getUsersByStatus(StatusEnum status);

	Optional<List<User>> getAllUsers();

	Optional<User> getUserByUserName(String userName);



}