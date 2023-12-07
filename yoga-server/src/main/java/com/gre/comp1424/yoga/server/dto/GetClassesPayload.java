package com.gre.comp1424.yoga.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetClassesPayload {
    private String userId;
    private String b2;
}
