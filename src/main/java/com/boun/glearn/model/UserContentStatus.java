package com.boun.glearn.model;

public class UserContentStatus {

    private Long contentId;
    private Boolean isCompleted;


    public UserContentStatus() {
    }

    public UserContentStatus(Long contentId, Boolean isCompleted) {
        this.contentId = contentId;
        this.isCompleted = isCompleted;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
