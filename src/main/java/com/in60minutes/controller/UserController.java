package com.in60minutes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in60minutes.exception.UserResourceNotFoundException;
import com.in60minutes.model.User;
import com.in60minutes.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userrepo;
	
	//get all users
	@GetMapping
	public List<User> getAllUsers(){
		return this.userrepo.findAll();
	}
	
	//get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value= "id") long userId) {
		return this.userrepo.findById(userId).orElseThrow(()-> new UserResourceNotFoundException("User not found with id : "+userId));
	}
	
	//create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userrepo.save(user);
	}
	
	//update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable ("id") long userId) {
		User existing = this.userrepo.findById(userId)
				.orElseThrow(() -> new UserResourceNotFoundException("User not found with id : "+userId));
		existing.setFirstName(user.getFirstName());
		existing.setLastName(user.getLastName());
		existing.setEmail(user.getEmail());
		return this.userrepo.save(existing);
	}
	
	//delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
		User existing = this.userrepo.findById(userId)
				.orElseThrow(() -> new UserResourceNotFoundException("User not found with id : "+userId));
		this.userrepo.delete(existing);
		return ResponseEntity.ok().build();
	}
}
