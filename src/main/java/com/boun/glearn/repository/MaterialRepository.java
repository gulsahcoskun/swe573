package com.boun.glearn.repository;

import com.boun.glearn.model.Content;
import com.boun.glearn.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Repository
public interface MaterialRepository extends JpaRepository<Material,Long> {

    List<Material> getMaterialByCreatedBy(String createdBy);

    @Query("select  material.contents from Material material where material.id = :materialId")
    List<Content> getContentsByMaterialId(@Param("materialId") Long materialId);

}
