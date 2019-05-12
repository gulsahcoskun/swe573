package com.boun.glearn.controller;
import com.boun.glearn.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("learn")
public class LearningController {


    @Autowired
    private LearnService learnService;

    @PostMapping("/progress")
    public ResponseEntity addProgress(@PathVariable("username") String username,
                                      @PathVariable("materialId") Long materialId,
                                      @PathVariable("contentId") Long contentId,
                                      @PathVariable("options") String options){
        return ResponseEntity.ok(learnService.addProgress(username,materialId,contentId,options));
    }


    @GetMapping("/isCompleted")
    public ResponseEntity isContentCompleted(@PathVariable("username") String username,
                                             @PathVariable("materialId") Long materialId,
                                             @PathVariable("contentId") Long contentId){
        return ResponseEntity.ok(learnService.isContentCompleted(username,materialId,contentId));
    }



}
