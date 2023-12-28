package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.LoginService;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	LoginService loginService;

	private BCryptPasswordEncoder passowrdEncoder= new BCryptPasswordEncoder();;
	
	@PostMapping("register/{username}")
	public ResponseEntity<String>register(@PathVariable String username,@RequestBody String password){
		String encryptedPassword=passowrdEncoder.encode(password);
		return  loginService.createUser(username, encryptedPassword);
	}
	
	@PostMapping("login/{username}")
	public ResponseEntity<String>login(@PathVariable String username,@RequestBody String password){
		return  loginService.userLogin(username, password);
	}
	
	@PostMapping("logout/{username}")
	public ResponseEntity<String>logout(@PathVariable String username,@RequestBody String password){
		return  loginService.userLogout(username);
	}
	
	
}
