package com.gre.comp1424.yoga.server;

import com.gre.comp1424.yoga.server.dto.*;

import java.util.List;

public interface ApplicationService {
    SubmitClassesResponse submitClasses(SubmitClassesPayload payload);

    List<ClassInfo> getClasses(GetClassesPayload payload);

    SubmitBookingsResponse submitBookings(SubmitBookingsPayload payload);
}
