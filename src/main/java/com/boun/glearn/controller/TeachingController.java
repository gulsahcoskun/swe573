package com.boun.glearn.controller;

import com.boun.glearn.model.Content;
import com.boun.glearn.model.Material;
import com.boun.glearn.model.Question;
import com.boun.glearn.model.ServiceResponse;
import com.boun.glearn.service.TeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("teach")
public class TeachingController {

    @Autowired
    private TeachService service;


    @GetMapping("/search/{keyword}")
    public ResponseEntity searchWiki(@PathVariable("keyword") String keyword){
        return ResponseEntity.ok(service.searchWiki(keyword));
    }


    @PostMapping("/create/material/")
    public ResponseEntity<ServiceResponse> createMaterial(@RequestBody @Valid Material material){
        return ResponseEntity.ok(service.addMaterial(material));
    }

    @PutMapping("/update/material/{id}")
    public ResponseEntity<ServiceResponse> updateMaterial(@RequestBody @Valid Material material, @PathVariable Long id){
        return ResponseEntity.ok(service.updateMaterial(material,id));
    }

    @DeleteMapping("/delete/material/{id}")
    public ResponseEntity deleteMaterial(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteMaterial(id));
    }


    @PostMapping("/create/content/{id}")
    public ResponseEntity<ServiceResponse> createContent(@RequestBody @Valid Content content,
                                                         @PathVariable("id") Long materialId){
        return ResponseEntity.ok(service.addContent(content,materialId));
    }

    @PutMapping("/update/content/{id}")
    public ResponseEntity<ServiceResponse> updateContent(@RequestBody @Valid Content content,
                                                         @PathVariable Long id){
        return ResponseEntity.ok(service.updateContent(content,id));
    }

    @DeleteMapping("/delete/content/{id}")
    public ResponseEntity deleteContent(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteContent(id));
    }

    @PostMapping("/create/question/{id}")
    public ResponseEntity<ServiceResponse> createQuestion(@RequestBody @Valid Question question,
                                                          @PathVariable("id") Long contentId){
        return ResponseEntity.ok(service.addQuestion(question,contentId));
    }

    @PutMapping("/update/question/{id}")
    public ResponseEntity<ServiceResponse> updateQuestion(@RequestBody @Valid Question question,
                                                          @PathVariable Long id){
        return ResponseEntity.ok(service.updateQuestion(question,id));
    }

    @DeleteMapping("/delete/question/{id}")
    public ResponseEntity deleteQuestion(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteQuestion(id));
    }


}
