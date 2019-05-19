package com.boun.glearn.controller;

import com.boun.glearn.model.Content;
import com.boun.glearn.model.Material;
import com.boun.glearn.model.MaterialSummary;
import com.boun.glearn.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("search")
public class SearchingController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/{username}")
    public ResponseEntity<List<MaterialSummary>> getMaterials(@PathVariable(name="username") String username){
        return ResponseEntity.ok(searchService.getAllMaterialSummaries(username));
    }

    @GetMapping("/content/{id}")
    public ResponseEntity<List<Content>> getContents(@PathVariable Long id){
        return ResponseEntity.ok(searchService.getContents(id));
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<MaterialSummary>> searchMaterials(@PathVariable(name = "keyword") String keyword) {
        return ResponseEntity.ok(searchService.searchMaterials(keyword));
    }

    @GetMapping("/createdBy/{createdBy}")
    public ResponseEntity<List<Material>> getCreatedMaterials(@PathVariable("createdBy") String createdBy){
        return ResponseEntity.ok(searchService.getCreatedMaterials(createdBy));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Material> getMaterialDetail(@PathVariable Long id){
        return ResponseEntity.ok(searchService.getMaterialDetail(id));
    }


    @GetMapping("/inProgress/{username}")
    public ResponseEntity getInProgressMaterials(@PathVariable("username") String username){
        return ResponseEntity.ok(searchService.getInProgressMaterial(username));
    }


    @GetMapping("/completed/{username}")
    public ResponseEntity getCompletedMaterials(@PathVariable("username") String username){
        return  ResponseEntity.ok(searchService.getCompletedMaterial(username));
    }

}
