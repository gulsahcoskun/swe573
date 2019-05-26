package com.boun.glearn.controller;
import com.boun.glearn.model.UserMaterialStatus;
import com.boun.glearn.model.UserProgressControl;
import com.boun.glearn.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("learn")
public class LearningController {

    private LearnService learnService;

    public LearningController(LearnService learnService){
        this.learnService = learnService;
    }

    @PostMapping("/progress")
    public ResponseEntity addProgress(@RequestBody @Valid UserProgressControl userProgressControl){
        return ResponseEntity.ok(learnService.addProgress(userProgressControl));
    }



    @GetMapping("/userStatus/{username}/{materialId}")
    public ResponseEntity<UserMaterialStatus> getUserStatus(@PathVariable("username") String username,
                                                            @PathVariable("materialId") Long materialId){
        return ResponseEntity.ok(learnService.findUserMaterialStatus(username,materialId));
    }






}
