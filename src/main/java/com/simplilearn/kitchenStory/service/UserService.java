package com.simplilearn.kitchenStory.service;

import com.simplilearn.kitchenStory.entity.User;
import com.simplilearn.kitchenStory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public List<User> getUsersByName(String name){
		return userRepository.findByNameStartingWith(name);
	}

	public Optional<User> getUsersByEmail(String email){
		return userRepository.findUserByEmail(email);
	}
}
