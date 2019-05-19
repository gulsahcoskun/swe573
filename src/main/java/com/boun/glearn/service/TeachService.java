package com.boun.glearn.service;

import com.boun.glearn.model.*;

import java.util.List;
import java.util.Set;

public interface TeachService {

    List<Keyword> searchWiki(String keyword);
    List<Material> getAllMaterials();
    Set<Content> getAllContents(Long materialId);
    Set<Question> getAllQuestions(Long contentId);
    Set<Keyword> getAllKeywords(Long contentId);

    ServiceResponse addMaterial(Material material);
    ServiceResponse updateMaterial(Material material,Long id);
    ServiceResponse deleteMaterial(Long id);

    ServiceResponse addContent(Content content, Long materialId);
    ServiceResponse updateContent(Content content,Long id);
    ServiceResponse deleteContent(Long id);

    ServiceResponse addQuestion(Question question, Long contentId);
    ServiceResponse updateQuestion(Question question, Long id);
    ServiceResponse deleteQuestion(Long id);



}
