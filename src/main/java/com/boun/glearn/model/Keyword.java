package com.boun.glearn.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "keyword")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "semantig_tag")
    private String semanticTag;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "instance_of")
    private String instanceOf;

    @Column(name = "instance_id")
    private String instanceId;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "created_date")
    private Date createdDate;

    public Keyword() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getSemanticTag() {
        return semanticTag;
    }

    public void setSemanticTag(String semanticTag) {
        this.semanticTag = semanticTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstanceOf() {
        return instanceOf;
    }

    public void setInstanceOf(String instanceOf) {
        this.instanceOf = instanceOf;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
