package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Question;
import com.example.demo.dao.QuestionDao;

@Service
public class QuestionService {
//	  private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

	
	private final QuestionDao questiondao;
	@Autowired
	public QuestionService( QuestionDao questiondao) {
		this.questiondao=questiondao;
	}
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
	        return new ResponseEntity<>(questiondao.findAll(),HttpStatus.OK);}
		catch (Exception e) {
			e.printStackTrace();
		}
		 return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	 public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
	        try {
	            return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

	    }
	public ResponseEntity<String> addQuestion(Question question) {
		  questiondao.save(question);
	        return new ResponseEntity<>("success",HttpStatus.CREATED);
		
	}
	public String deleteQuestion(Integer id) {
		// TODO Auto-generated method stub
		 questiondao.deleteById(id);
		return "delete";
	}
	public Optional<Question> findById(Integer id) {
		// TODO Auto-generated method stub
//		System.out.println(questiondao.findById(id));
		return questiondao.findById(id);
	}
	public String updateQuestion(Question q) {
		// TODO Auto-generated method stub
		questiondao.save(q);
		return "success";
		
	}

	}


