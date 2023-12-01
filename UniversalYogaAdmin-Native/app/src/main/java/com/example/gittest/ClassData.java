package com.example.gittest;

public class ClassData {
    private long id;
    private String teacher;
    private String date;
    private String comments;
    private long courseId;

    public ClassData() {
    }


    public ClassData(long id, String teacher, String date, String comments, long courseId) {
        this.id = id;
        this.teacher = teacher;
        this.date = date;
        this.comments = comments;
        this.courseId = courseId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
