package com.gre.comp1424.yoga.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitClassesResponse {
    private String uploadResponseCode;
    private String userId;
    private int number;
    private String courses;
    private String message;
}
