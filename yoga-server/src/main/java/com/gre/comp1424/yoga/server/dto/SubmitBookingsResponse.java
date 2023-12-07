package com.gre.comp1424.yoga.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitBookingsResponse {
    private String uploadResponseCode;
    private String userId;
    private int number;
    private String bookings;
    private String message;
}
