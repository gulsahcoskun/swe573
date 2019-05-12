package com.boun.glearn.service;

import com.boun.glearn.model.Content;
import com.boun.glearn.model.Material;
import com.boun.glearn.model.MaterialSummary;

import java.util.List;

public interface SearchService {

    List<MaterialSummary> getAllMaterialSummaries();
    List<Content> getContents(Long materialId);
    List<MaterialSummary> searchMaterials(String keyword);
    Material getMaterialDetail(Long materialId);
    List<MaterialSummary> getCreatedMaterials(String createdBy);
    List<MaterialSummary> getInProgressMaterial(String username);
    List<MaterialSummary> getCompletedMaterial(String username);


}
