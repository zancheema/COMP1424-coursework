package com.zancheema.comp1424.universalyoga.universalyoga.admin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailDao {
    private final YogaAdminDbHelper dbHelper;

    public CourseDetailDao(Context context) {
        dbHelper = new YogaAdminDbHelper(context);
    }

    public boolean save(CourseDetail courseDetail) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("day_of_week", courseDetail.getDayOfWeek());
        values.put("time", courseDetail.getTime());
        values.put("capacity", courseDetail.getCapacity());
        values.put("duration", courseDetail.getDuration());
        values.put("price_per_class", courseDetail.getPricePerClass());
        values.put("type_of_class", courseDetail.getTypeOfClass());
        values.put("description", courseDetail.getDescription());

        long newRowId = db.insert("course_detail", null, values);
        return newRowId != -1;
    }

    public List<CourseDetail> getCourseDetails() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projections = new String[]{
                "day_of_week", "time", "capacity", "duration", "price_per_class",
                "type_of_class", "type_of_class", "description"};
        String sortOrder = "day_of_week DESC";

        Cursor cursor = db.query(
                "course_detail",
                projections,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List<CourseDetail> courseDetails = new ArrayList<>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String dayOfWeek = cursor.getString(cursor.getColumnIndexOrThrow("day_of_week"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            int capacity = cursor.getInt(cursor.getColumnIndexOrThrow("capacity"));
            int duration = cursor.getInt(cursor.getColumnIndexOrThrow("duration"));
            double pricePerClass = cursor.getDouble(cursor.getColumnIndexOrThrow("price_per_class"));
            String typeOfClass = cursor.getString(cursor.getColumnIndexOrThrow("type_of_class"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            CourseDetail courseDetail = new CourseDetail(id, dayOfWeek, time, capacity, duration, pricePerClass, typeOfClass, description);
            courseDetails.add(courseDetail);
        }

        return courseDetails;
    }
}
