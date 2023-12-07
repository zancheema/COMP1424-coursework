package com.gre.comp1424.yoga.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitBookingsPayload {
    private JsonPayload jsonPayload;
    private String b3;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JsonPayload {
        private String userId;
        private List<Booking> bookingList;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Booking {
        private int instanceId;
    }
}
