package com.boun.glearn.model;

import java.util.List;

public class UserMaterialStatus {

    private Long materialId;
    private Boolean isCompleted;
    List<UserContentStatus> userContentStatusList;

    public UserMaterialStatus() {
    }

    public UserMaterialStatus(Long materialId, Boolean isCompleted, List<UserContentStatus> userContentStatusList) {
        this.materialId = materialId;
        this.isCompleted = isCompleted;
        this.userContentStatusList = userContentStatusList;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public List<UserContentStatus> getUserContentStatusList() {
        return userContentStatusList;
    }

    public void setUserContentStatusList(List<UserContentStatus> userContentStatusList) {
        this.userContentStatusList = userContentStatusList;
    }
}
