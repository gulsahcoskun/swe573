package com.boun.glearn.service;

import com.boun.glearn.model.*;
import com.boun.glearn.repository.MaterialRepository;
import com.boun.glearn.repository.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private MaterialRepository materialRepository;


    @Override
    public List<StudentTrackData> getStudentTrackData(String username) {
       List<UserProgress> allProgress = userProgressRepository.getUserProgressByUsername(username);
       List<StudentTrackData> studentTrackDataList = new ArrayList<>();

       if(allProgress != null && !allProgress.isEmpty()){
           Set<Long> materialIds = allProgress.stream().map(p -> p.getMaterialId()).collect(Collectors.toSet());

           for(Long materialId : materialIds){
               List<UserProgress> materialProgress = userProgressRepository.getAllByUsernameAndMaterialId(username,materialId);
               Optional<Material> material = materialRepository.findById(materialId);

               if(material.isPresent()){
                   StudentTrackData trackData = new StudentTrackData();
                   trackData.setMaterialName(material.get().getTitle());

                   int completedContentCount = 0;

                   for(Content content : material.get().getContents()){
                       for(UserProgress progress : materialProgress){
                           if(progress.getContentId() == content.getId() && progress.isCompleted()){
                               completedContentCount ++;
                           }
                       }
                   }

                   trackData.setCompletionRatio(100 * completedContentCount / material.get().getContents().size());
                   studentTrackDataList.add(trackData);
               }

           }
       }
       return studentTrackDataList;
    }


    @Override
    public List<TeacherTrackData> getTeacherTrackData(String username) {
        List<Material> createdMaterials = materialRepository.getMaterialByCreatedBy(username);
        List<TeacherTrackData> teacherTrackDataList = new ArrayList<>();
        if(createdMaterials!=null && !createdMaterials.isEmpty()){
            for(Material material : createdMaterials){
                List<UserProgress> progressList = userProgressRepository.getAllByMaterialId(material.getId());
                int numberOfStudents = 0;
                if(progressList != null && !progressList.isEmpty()){
                    numberOfStudents = progressList.stream().map(p -> p.getUsername()).collect(Collectors.toSet()).size();
                }
                TeacherTrackData trackData = new TeacherTrackData();
                trackData.setMaterialName(material.getTitle());
                trackData.setNumberOfStudents(numberOfStudents);
                teacherTrackDataList.add(trackData);
            }
        }
        return teacherTrackDataList;
    }

}