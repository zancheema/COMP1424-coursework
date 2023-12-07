package com.gre.comp1424.yoga.server;

import com.gre.comp1424.yoga.server.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private static int BOOKING_INSTANCE_ID = 0;

    private Map<String, SubmitClassesPayload> classes = new HashMap<>();
    private Map<String, SubmitBookingsPayload> bookings = new HashMap<>();
    private Map<SubmitClassesPayload.CourseClass, Integer> bookingInstanceIds = new HashMap<>();

    @Override
    public SubmitClassesResponse submitClasses(SubmitClassesPayload payload) {
        classes.put(payload.getJsonpayload().getUserId(), payload);
        payload.getJsonpayload().getDetailList()
                .forEach(detail -> {
                    detail.getClassList()
                            .forEach(courseClass -> {
                                bookingInstanceIds.put(courseClass,++BOOKING_INSTANCE_ID);
                            });
                });
        String courses = payload.getJsonpayload().getDetailList()
                .stream()
                .map(detail -> "" + detail.getDayOfWeek() + "(" + detail.getTimeOfDay() + ") ")
                .reduce((a, b) -> a + b)
                .get()
                .trim();
        return new SubmitClassesResponse(
                "SUCCESS",
                payload.getJsonpayload().getUserId(),
                2,
                courses,
                "successful upload - all done!"
        );
    }

    @Override
    public List<ClassInfo> getClasses(GetClassesPayload payload) {
        int instanceId = 0;
        List<ClassInfo> classInfos = new ArrayList<>();
        SubmitClassesPayload userClasses = classes.get(payload.getUserId());
        if (userClasses == null) return classInfos;
        for (SubmitClassesPayload.CourseDetail courseDetail : userClasses.getJsonpayload().getDetailList()) {
            for (SubmitClassesPayload.CourseClass courseClass : courseDetail.getClassList()) {
                ClassInfo classInfo = new ClassInfo(++instanceId, courseClass.getDate(), courseClass.getTeacher(), courseDetail.getDayOfWeek(), courseDetail.getTimeOfDay());
                classInfos.add(classInfo);
            }
        }
        return classInfos;
    }

    @Override
    public SubmitBookingsResponse submitBookings(SubmitBookingsPayload payload) {
        bookings.put(payload.getJsonPayload().getUserId(), payload);

        Set<Integer> payloadInstanceIds = payload.getJsonPayload().getBookingList()
                .stream()
                .map(b -> b.getInstanceId())
                .collect(Collectors.toSet());
        String bookingsStr = bookingInstanceIds.entrySet()
                .stream()
                .filter(b -> payloadInstanceIds.contains(b.getValue()))
                .map(b -> b.getKey())
                .map(b -> "" + b.getDate() + " - " + b.getTeacher())
                .collect(Collectors.joining(", "));
        return new SubmitBookingsResponse(
                "SUCCESS",
                payload.getJsonPayload().getUserId(),
                2,
                bookingsStr,
                "Successfully booked!"
        );
    }
}
