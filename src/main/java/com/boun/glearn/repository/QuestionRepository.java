package com.boun.glearn.repository;

import com.boun.glearn.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
public interface QuestionRepository extends JpaRepository<Question,Long> {
}
