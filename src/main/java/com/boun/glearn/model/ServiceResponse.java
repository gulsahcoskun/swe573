package com.boun.glearn.model;

public class ServiceResponse {

    private boolean isSuccess;
    private Object message;


    public ServiceResponse(boolean isSuccess, Object message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }


    public ServiceResponse() {
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
