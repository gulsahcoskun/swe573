package com.boun.glearn.model;

import java.util.List;

public class StudentTrackData {

    private String materialName;
    private Integer completionRatio;
    private List<DetailedTrackData> detailedTrackDataList;

    public StudentTrackData() {
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


    public List<DetailedTrackData> getDetailedTrackDataList() {
        return detailedTrackDataList;
    }

    public void setDetailedTrackDataList(List<DetailedTrackData> detailedTrackDataList) {
        this.detailedTrackDataList = detailedTrackDataList;
    }
}
