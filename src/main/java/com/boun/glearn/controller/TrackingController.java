package com.boun.glearn.controller;

import com.boun.glearn.model.StudentTrackData;
import com.boun.glearn.model.TeacherTrackData;
import com.boun.glearn.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("track")
public class TrackingController {

    @Autowired
    private TrackService trackService;

    @GetMapping("/student/{username}")
    public ResponseEntity<List<StudentTrackData>> getStudentTrackData(@PathVariable("username") String username){
        return ResponseEntity.ok(trackService.getStudentTrackData(username));
    }


    @GetMapping("/teacher/{username}")
    public ResponseEntity<List<TeacherTrackData>> getTeacherTrackData(@PathVariable("username") String username){
        return ResponseEntity.ok(trackService.getTeacherTrackData(username));
    }


}
