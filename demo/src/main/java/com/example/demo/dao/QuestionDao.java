package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

	List<Question>findByCategory(String category);//category is part of table therefore we need not write any query

	 @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	List<Question> findRandomQuestionByCategory(String category,int numQ);
}
