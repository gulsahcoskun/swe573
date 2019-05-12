package com.boun.glearn.repository;

import com.boun.glearn.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress,Long> {

    List<UserProgress> getUserProgressByUsername(String username);
    List<UserProgress> getAllByUsernameAndMaterialId(String username, Long materialId);

}
