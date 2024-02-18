package com.example.ShoppingCartApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ShoppingCartApp.entities.User;
import com.example.ShoppingCartApp.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public boolean authenticate(String username, String password) {
    	User user=userRepository.findByUsername(username);
    	
    	if (user != null && user.getPassword().equals(password))
    		return true;
    	return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public void updatePassword(String username, String password) {
    	User user=userRepository.findByUsername(username);
    	
    	user.setPassword(password);
		userRepository.save(user);
    }
}
