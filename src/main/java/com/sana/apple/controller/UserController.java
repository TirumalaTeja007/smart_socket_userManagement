package com.sana.apple.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sana.apple.dto.ApiResponse;
import com.sana.apple.enums.StatusEnum;
import com.sana.apple.exception.EmptyResourceListException;
import com.sana.apple.exception.ResourceNotFoundException;
import com.sana.apple.exception.ResourceRegistrationException;
import com.sana.apple.exception.ResourceUpdationException;
import com.sana.apple.exception.UserLoginException;
import com.sana.apple.model.User;
import com.sana.apple.service.IUserService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {

	private static Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	IUserService userService;
	

	@PostMapping(value = "/user")
	public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) {
		logger.info("UserController.registerUser()::Users:" + user);
		
			return userService.addUser(user).map(newUser -> {
				ApiResponse apiResponse = null;
				logger.info("registerUser returned [API[: " + newUser);
				apiResponse=new ApiResponse("User is added successfully.", true);
				apiResponse.setData(newUser);
				return ResponseEntity.ok(apiResponse);
			}).orElseThrow(() -> new ResourceRegistrationException(user.getName(), "User registration is failed"));
	}

	@PutMapping(value = "/user")
	public ResponseEntity<ApiResponse> updateUser(@RequestBody User user) {
		
		logger.info("UserController.updateUser()::Users:" + user);
			 return userService.updateUser(user).map(newUser -> {
		            logger.info("updateUser returned [API[: " + newUser);
		            return ResponseEntity.ok(new ApiResponse("User updated successfully.", true));
		        }).orElseThrow(() -> new ResourceUpdationException(user.getName(), "There is a problem while updating User"));
	}
	
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
		
			 return userService.getUser(id).map(user -> {
				 logger.info("getUser returned [API[: " + user);
				 return ResponseEntity.ok(user);
			 }).orElseThrow(() -> new ResourceNotFoundException("User", id.toString(), id));
	}

	@GetMapping(value = "/user/mobile/{mobileNumber}")
	public ResponseEntity<?> getUser(@PathVariable("mobileNumber") String mobileNumber) {
		
			 return userService.getUserByMobileNumber(mobileNumber).map(user -> {
		            logger.info("getUser returned [API[: " + user);
		            return ResponseEntity.ok(user);
		        }).orElseThrow(() -> new ResourceNotFoundException("User", mobileNumber, mobileNumber));
	}

	@GetMapping(value = "/user/userName/{userName}")
	public ResponseEntity<?> getUserByUserName(@PathVariable("userName") String userName) {
		
			 return userService.getUserByUserName(userName).map(user -> {
		            logger.info("getUser returned [API[: " + user);
		            return ResponseEntity.ok(user);
		        }).orElseThrow(() -> new ResourceNotFoundException("User", userName, userName));
	}
	
	
	@DeleteMapping(value = "/user")
	public ResponseEntity<?> deleteUser(@RequestBody User user) {
		return userService.deleteUser(user).map(user1 -> {
			logger.info("deleteUser returned [API[: " + user);
			return ResponseEntity.ok(new ApiResponse("User is deleted successfully.", true));
		}).orElseThrow(() -> new ResourceNotFoundException("user", user.getId().toString(), user.getId()));

	}

	/* Get only ACTIVE status data only */
	
	@GetMapping(value = "/user/all")
	public ResponseEntity<?> getActiveUsers() {
		
		ApiResponse apiResponse = null;
		StatusEnum status = StatusEnum.ACTIVE;
		Optional<List<User>> userList = null;
		logger.info("userController.getusers()  :: status : "+status);
			userList = userService.getAllUsers();
	        userList.orElseThrow(() -> new EmptyResourceListException("No users are found"));
	        return ResponseEntity.ok(userList.get());
	}
	
	/* Get all ACTIVE/IN_ACTIVE status data */
	
	@GetMapping(value = "/user/getall")
	public ResponseEntity<?> getAllUsers() {
		logger.info("userController.getAllUsers()  :: status : ");
		
		ApiResponse apiResponse = null;
		Optional<List<User>> userList = null;
			userList = userService.getAllUsers();
	        userList.orElseThrow(() -> new EmptyResourceListException("No users are found"));
	        return ResponseEntity.ok(userList.get());
	}	
	

	@PostMapping(value = "/user/login")
	public ResponseEntity<ApiResponse> loginUser(@RequestBody User user) {
		logger.info("UserController.loginUser()::User:" + user);
		
		ApiResponse apiResponse = null;
			return userService.loginUser(user).map(authUser -> {
				logger.info("loginUser returned [API[: " + authUser);
				Optional<User> loggedInUser = userService.getUser(authUser.getName());
				ApiResponse response =new ApiResponse("User authenticated successfully.", true);
				response.setData(loggedInUser);
				return ResponseEntity.ok(response);
			}).orElseThrow(() -> new UserLoginException("Invalid login credentials."));
	}
	
	
	
	
	
	
	

}
