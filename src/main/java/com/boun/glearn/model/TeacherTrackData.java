package com.boun.glearn.model;

public class TeacherTrackData {

    private String materialName;
    private Integer numberOfStudents;

    public TeacherTrackData() {
    }

    public TeacherTrackData(String materialName, Integer numberOfStudents) {
        this.materialName = materialName;
        this.numberOfStudents = numberOfStudents;
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
}
