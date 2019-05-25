package com.boun.glearn.model;

import java.util.List;

public class TeacherTrackData {

    private String materialName;
    private Integer numberOfStudents;
    private List<DetailedTrackData> detailedTrackDataList;

    public TeacherTrackData() {
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }


    public List<DetailedTrackData> getDetailedTrackDataList() {
        return detailedTrackDataList;
    }

    public void setDetailedTrackDataList(List<DetailedTrackData> detailedTrackDataList) {
        this.detailedTrackDataList = detailedTrackDataList;
    }
}
