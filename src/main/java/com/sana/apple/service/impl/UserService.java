package com.sana.apple.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import com.sana.apple.enums.StatusEnum;
import com.sana.apple.exception.ResourceAlreadyInUseException;
import com.sana.apple.exception.UserLoginException;
import com.sana.apple.model.User;
import com.sana.apple.repository.UserRepository;
import com.sana.apple.service.IUserService;


@Service
public class UserService implements IUserService {
	private static Logger logger = LogManager.getLogger(UserService.class);


	@Autowired
	UserRepository userRepository;
	Random randomGenerator = new Random();

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	private static AtomicLong idCounter = new AtomicLong();

	/**
	 * Save user info in database
	 * 
	 * @return - returns User object
	 */
	@Override
	public Optional<User> addUser(User user) {
		
		String mobileNumber = user.getMobileNumber();
		String userName = user.getUserName();
		String email = user.getEmail();
		if (mobileAlreadyExists(mobileNumber)) {
			logger.error("MobileNumber already exists: " + mobileNumber);
			throw new ResourceAlreadyInUseException("MobileNumber", "number", mobileNumber);
		}if(emailAlreadyExists(email)) {
			logger.error("Email already exists: " + email);
			throw new ResourceAlreadyInUseException("Email", "number", email);
		} else {
			String str = "SS";
			user.setUserName(str+"-"+generatePassword());
			user.setCreatedTime(new Date());			user.setUpdatedTime(new Date());//			user.setPassword(generatePassword());			User newUser = userRepository.save(user);			logger.info("===== User saved successfully =====" + newUser);			return Optional.ofNullable(newUser);
		}
	}


	private String generatePassword() {
		return String.format("%04d", randomGenerator.nextInt(10000));
	}

	@Override
	@Transactional
	public Optional<User> updateUser(User user) {
		user.setUpdatedTime(new Date());
		user.setPassword(user.getPassword());
		user.setVersion(user.getVersion()+1);
		
		User updatedUser = userRepository.save(user);
		logger.info("===== User updated successfully =====");
		return Optional.ofNullable(updatedUser);
	}

	@Override
	public Optional<List<User>> getUsersByStatus(StatusEnum status) {
		return userRepository.findByStatus(status);
	}

	public Optional<User> deleteUser(User user) {
		userRepository.delete(user);
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> getUser(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> getUserByMobileNumber(String mobileNumber) {
		return userRepository.findByMobileNumber(mobileNumber);
	}
	
	@Override
	public Optional<User> getUser(String userName) {
		return userRepository.findByUserName(userName);
	}

	public Boolean mobileAlreadyExists(String mobileNumber) {
		return userRepository.findByMobileNumber(mobileNumber).isPresent();
	}
	
	public Boolean userNameAlreadyExists(String userName) {
		return userRepository.findByUserName(userName).isPresent();
	}
	
	public Boolean emailAlreadyExists(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	@Override
	public Optional<Authentication> loginUser(User user) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
		try {
			if(user.getStatus() == StatusEnum.ACTIVE) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
				usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, user.getPassword(), userDetails.getAuthorities());
			}else {
				throw new UserLoginException("Couldn't login user [" + user + "]");
			}
		}catch(Exception e) {
			throw new UserLoginException("Couldn't login user [" + user.getStatus() + "]");
		}
			return Optional.ofNullable(authenticationManager.authenticate(usernamePasswordAuthenticationToken));
		}

	@Override
	public Optional<List<User>> getAllUsers() {
		return Optional.ofNullable(userRepository.findAll());
	}


	@Override
	public Optional<User> getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}



}
