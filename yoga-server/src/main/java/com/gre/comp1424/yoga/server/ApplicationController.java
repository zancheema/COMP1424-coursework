package com.gre.comp1424.yoga.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.comp1424.yoga.server.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
@Slf4j
public class ApplicationController {
    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping(path = "/SubmitClasses")
    public SubmitClassesResponse submitClasses(@RequestParam("jsonpayload") String jsonpayload, @RequestParam("b1") String b1) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SubmitClassesPayload.JsonPayload jsonPayload = objectMapper.readValue(jsonpayload, SubmitClassesPayload.JsonPayload.class);
        return service.submitClasses(new SubmitClassesPayload(jsonPayload, b1));
    }

    @PostMapping("/GetInstances")
    public List<ClassInfo> getClasses(@RequestBody GetClassesPayload payload) {
        return service.getClasses(payload);
    }

    @PostMapping("/SubmitBookings")
    public SubmitBookingsResponse submitBookings(@RequestBody SubmitBookingsPayload payload) throws JsonProcessingException {
        return service.submitBookings(payload);
    }
}
