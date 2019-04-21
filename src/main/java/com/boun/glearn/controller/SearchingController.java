package com.boun.glearn.controller;

import com.boun.glearn.model.MaterialSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchingController {

    @GetMapping("/")
    public ResponseEntity<List<MaterialSummary>> getPopularMaterials(){
        return null;
    }

    @RequestMapping("/{keyword}")
    public ResponseEntity<List<MaterialSummary>> searchMaterials(@PathVariable(name = "keyword") String keyword) {
        return null;
    }



}
