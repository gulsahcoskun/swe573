package com.boun.glearn.model;

import java.util.List;

public class MaterialSummary {

    private Long materialId;
    private String image;
    private String materialName;
    private List<String> keywordList;
    private String description;
    private String createdBy;
    private String detail;


    public MaterialSummary() {
    }

    public MaterialSummary(Long materialId, String image, String materialName, List<String> keywordList,
                           String description, String createdBy, String detail) {
        this.materialId = materialId;
        this.image = image;
        this.materialName = materialName;
        this.keywordList = keywordList;
        this.description = description;
        this.createdBy = createdBy;
        this.detail = detail;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public List<String> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<String> keywordList) {
        this.keywordList = keywordList;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
