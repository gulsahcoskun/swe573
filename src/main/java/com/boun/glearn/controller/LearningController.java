package com.boun.glearn.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("learn")
public class LearningController {


    @PostMapping("/enroll/")
    public ResponseEntity enrollToMaterial(){
        return null;
    }


}
