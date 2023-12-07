package com.gre.comp1424.yoga.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfo {
    private int instanceId;
    private String date;
    private String teacher;
    private String classDay;
    private String classTime;
}
