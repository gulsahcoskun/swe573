package com.boun.glearn.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@Entity
@Table(name = "user_progress")
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "material_id")
    private Long materialId;

    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "date_started")
    private Date dateStarted;

    @Column(name = "date_updated")
    private Date dateUpdated;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Column(name = "number_of_try")
    private Long numberOfTry;

    public UserProgress() {
    }

    public UserProgress(String username, Long materialId, Long contentId, Date dateStarted, Date dateUpdated, boolean isCompleted, Long numberOfTry) {
        this.username = username;
        this.materialId = materialId;
        this.contentId = contentId;
        this.dateStarted = dateStarted;
        this.dateUpdated = dateUpdated;
        this.isCompleted = isCompleted;
        this.numberOfTry = numberOfTry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Long getNumberOfTry() {
        return numberOfTry;
    }

    public void setNumberOfTry(Long numberOfTry) {
        this.numberOfTry = numberOfTry;
    }
}
