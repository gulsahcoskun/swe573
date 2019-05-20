package com.boun.glearn.model;

public class StudentTrackData {

    private String materialName;
    private Integer completionRatio;

    public StudentTrackData() {
    }

    public StudentTrackData(String materialName, Integer completionRatio) {
        this.materialName = materialName;
        this.completionRatio = completionRatio;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getCompletionRatio() {
        return completionRatio;
    }

    public void setCompletionRatio(Integer completionRatio) {
        this.completionRatio = completionRatio;
    }
}
