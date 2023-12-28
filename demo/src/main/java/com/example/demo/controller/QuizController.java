package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.QuestionWrapper;
import com.example.demo.Response;
import com.example.demo.service.LoginService;
import com.example.demo.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	@Autowired
	LoginService loginService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category,
			@RequestParam int numQ,@RequestParam String title){
		return quizService.createQuiz(category,numQ,title);
	}
	
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id,@RequestParam String username){
		if(loginService.isUserLoggedIn(username)) {
		return quizService.getQuizQuestions(id);}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);	
	}
	
	 @PostMapping("submit/{id}")
	    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
	        return quizService.calculateResult(id, responses);
	    }
}
