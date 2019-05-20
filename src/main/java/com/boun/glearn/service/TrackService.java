package com.boun.glearn.service;

import com.boun.glearn.model.StudentTrackData;
import com.boun.glearn.model.TeacherTrackData;

import java.util.List;

public interface TrackService {

    List<StudentTrackData> getStudentTrackData(String username);

    List<TeacherTrackData> getTeacherTrackData(String username);

}
