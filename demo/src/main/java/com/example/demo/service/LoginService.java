package com.example.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.demo.User;
import com.example.demo.dao.UserRepository;

@Service
public class LoginService {

	@Autowired
	UserRepository userRepository;
	
	private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	private final HashSet<String> loggedInUsers = new HashSet<>();
	public ResponseEntity<String>createUser(String username,String password){
		 Optional<User>u=userRepository.findByUsername(username);
		 User user=new User();
		if(!u.isPresent()) {
			user.setUsername(username);
			user.setPassword(password);
			userRepository.save(user);
			return new ResponseEntity<String>("user created",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("user exists",HttpStatus.FOUND);
	}

	public ResponseEntity<String> userLogin(String username, String enteredpassword) {
		// TODO Auto-generated method stub
		 Optional<User>u=userRepository.findByUsername(username);
		 if(u.isPresent()) {
		String storedPassword=u.get().getPassword();
		 if (passwordEncoder.matches(enteredpassword,storedPassword)) {
			 loggedInUsers.add(username);
			 return new ResponseEntity<String>("user logged in",HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<String>("unauthorized user",HttpStatus.UNAUTHORIZED);
		 }}
		 return new ResponseEntity<String>("user not found",HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String> userLogout(String username) {
        if (isUserLoggedIn(username)) {
            loggedInUsers.remove(username);  
            return new ResponseEntity<>("User logged out", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User is not logged in", HttpStatus.BAD_REQUEST);
        }
    }

    public boolean isUserLoggedIn(String username) {
        
        return loggedInUsers.contains(username);
    }

	
	
}
