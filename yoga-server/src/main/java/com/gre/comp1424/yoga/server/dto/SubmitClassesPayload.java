package com.gre.comp1424.yoga.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitClassesPayload {
    private JsonPayload jsonpayload;
    private String b1;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JsonPayload {
        private String userId;
        private List<CourseDetail> detailList;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseDetail {
        private String dayOfWeek;
        private String timeOfDay;
        private List<CourseClass> classList;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseClass {
        private String date;
        private String teacher;
    }
}
