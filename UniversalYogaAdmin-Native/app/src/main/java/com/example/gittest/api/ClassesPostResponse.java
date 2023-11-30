package com.example.gittest.api;

public class ClassesPostResponse {
    private String uploadResponseCode;
    private String userId;
    private int number;
    private String courses;
    private String message;

    public ClassesPostResponse() {
    }

    public ClassesPostResponse(String uploadResponseCode, String userId, int number, String courses, String message) {
        this.uploadResponseCode = uploadResponseCode;
        this.userId = userId;
        this.number = number;
        this.courses = courses;
        this.message = message;
    }

    public String getUploadResponseCode() {
        return uploadResponseCode;
    }

    public void setUploadResponseCode(String uploadResponseCode) {
        this.uploadResponseCode = uploadResponseCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
