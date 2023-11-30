package com.example.gittest;

public class ClassData {
    private long id;
    private String teacher;
    private String date;
    private long courseId;

    public ClassData() {
    }

    public ClassData(long id, String teacher, String date, long courseId) {
        this.id = id;
        this.teacher = teacher;
        this.date = date;
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

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
