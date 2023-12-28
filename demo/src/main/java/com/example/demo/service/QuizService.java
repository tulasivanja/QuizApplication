package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Question;
import com.example.demo.QuestionWrapper;
import com.example.demo.Quiz;
import com.example.demo.Response;
import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		// TODO Auto-generated method stub
		
		List<Question>questions=questionDao.findRandomQuestionByCategory(category,numQ);
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<String>("created",HttpStatus.CREATED);
	}
	public ResponseEntity<List<QuestionWrapper>>getQuizQuestions(Integer id) {
		// TODO Auto-generated method stub
		Optional<Quiz>quiz=quizDao.findById(id);
		List<Question>questionFromDb=quiz.get().getQuestions();
		List<QuestionWrapper>questionsForUser=new ArrayList<>();
		for(Question q:questionFromDb) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionsForUser.add(qw);
			}
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
		
	}
	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
