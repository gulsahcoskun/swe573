package com.boun.glearn.repository;

import com.boun.glearn.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
public interface OptionRepository extends JpaRepository<Option,Long> {
}
