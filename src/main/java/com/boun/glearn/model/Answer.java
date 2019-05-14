package com.boun.glearn.model;

public class Answer {

    private Long questionId;
    private Long optionId;

    public Answer(Long questionId, Long optionId) {
        this.questionId = questionId;
        this.optionId = optionId;
    }


    public Answer() {
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }
}
