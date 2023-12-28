package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Question;
import com.example.demo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User>findByUsername(String category);
}
