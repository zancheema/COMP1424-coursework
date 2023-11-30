package com.example.gittest.api;

import java.util.List;

public class ClassesPayload {
    private String userId;
    List<Detail> detailList;

    public ClassesPayload() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        return "ClassesPayload{" +
                "userId='" + userId + '\'' +
                ", detailList=" + detailList +
                '}';
    }

    public static class Detail {
        private String dayOfWeek;
        private String timeOfDay;
        private List<ClassInfo> classList;

        public Detail() {
        }

        public Detail(String dayOfWeek, String timeOfDay, List<ClassInfo> classList) {
            this.dayOfWeek = dayOfWeek;
            this.timeOfDay = timeOfDay;
            this.classList = classList;
        }

        public String getDayOfWeek() {
            return dayOfWeek;
        }

        public void setDayOfWeek(String dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public String getTimeOfDay() {
            return timeOfDay;
        }

        public void setTimeOfDay(String timeOfDay) {
            this.timeOfDay = timeOfDay;
        }

        public List<ClassInfo> getClassList() {
            return classList;
        }

        public void setClassList(List<ClassInfo> classList) {
            this.classList = classList;
        }

        @Override
        public String toString() {
            return "Detail{" +
                    "dayOfWeek='" + dayOfWeek + '\'' +
                    ", timeOfDay='" + timeOfDay + '\'' +
                    ", classList=" + classList +
                    '}';
        }
    }

    public static class ClassInfo {
        private String date;
        private String teacher;

        public ClassInfo() {
        }

        public ClassInfo(String date, String teacher) {
            this.date = date;
            this.teacher = teacher;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        @Override
        public String toString() {
            return "ClassInfo{" +
                    "date='" + date + '\'' +
                    ", teacher='" + teacher + '\'' +
                    '}';
        }
    }
}
