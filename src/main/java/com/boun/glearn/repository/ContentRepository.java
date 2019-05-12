package com.boun.glearn.repository;

import com.boun.glearn.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
public interface ContentRepository extends JpaRepository<Content,Long> {


}
