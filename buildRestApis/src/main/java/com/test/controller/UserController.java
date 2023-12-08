package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.User;
import com.test.exceptions.ResourceNotFoundException;
import com.test.repository.UserRepo;

@RestController
public class UserController {
	
	@Autowired
	private UserRepo  userRepo;
	
 // create user
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userRepo.save(newUser);
	}
	
 // get user list
	@GetMapping("/users")
	List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
// get single user
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Long id) {
		return userRepo.findById(id)
				             .orElseThrow(()->new ResourceNotFoundException(id));
				             
	}
	
// update user details
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) {
		return userRepo.findById(id)
				       .map(user->{
				    	   user.setName(newUser.getName());
				    	   user.setEmail(newUser.getEmail());
				    	   user.setPassword(newUser.getPassword());
				    	   user.setAddress(newUser.getAddress());
				    	   return userRepo.save(user);
				       })
				       .orElseThrow(()->new ResourceNotFoundException(id));
	}
	
// delete user
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userRepo.existsById(id)) {
			 throw new ResourceNotFoundException(id);
		}
		userRepo.deleteById(id);
		return "user has been deleted for id=" +id;
	}
}
