package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.Question;
import com.example.demo.service.QuestionService;

@RestController   //used to handle http requests
@RequestMapping("question")

public class QuestionController {

	
	private final QuestionService questionService;
	@Autowired
	public QuestionController( QuestionService questionService) {
		this.questionService=questionService;
	}
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {

		return questionService.getAllQuestions();
	}
	
	
	@GetMapping("category/{category}")
	public  ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {

		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		
		 return questionService.addQuestion(question);
		
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteQuestion(@PathVariable Integer id) {
		
		 return questionService.deleteQuestion(id);
		
	}
	
	@PutMapping("update/{id}")
	public String updateQuestion(@PathVariable Integer id, @RequestParam(name = "questionTitle", required = false)
	String param1,
@RequestParam(name = "option1", required = false) String param2,
@RequestParam(name = "option2", required = false) String param3,
@RequestParam(name = "option3", required = false) String param4,
@RequestParam(name = "option4", required = false) String param5,
@RequestParam(name = "rightAnswer", required = false) String param6,
@RequestParam(name = "difficultyLevel", required = false) String param7,
@RequestParam(name = "category", required = false) String param8) {
	Optional<Question>o=questionService.findById(id);
//	System.out.println(o.isPresent());
	if(o.isPresent()) {
		
		Question q=o.get();
		if(param1!=null) {
			q.setOption1(param1);
		}
		if(param2!=null) {
			q.setOption1(param2);
		}
		if(param3!=null) {
			q.setOption1(param3);
		}
		if(param4!=null) {
			q.setOption1(param4);
		}
		if(param5!=null) {
			q.setOption1(param5);
		}
		if(param6!=null) {
			q.setOption1(param6);
		}
		if(param7!=null) {
			q.setOption1(param7);
		}
		if(param8!=null) {
			q.setOption1(param8);
		}
		return questionService.updateQuestion(q);
	}
		 return "unable to update";
		
	}
}
