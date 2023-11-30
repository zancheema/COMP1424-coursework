package com.example.gittest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gittest.Course;
import com.example.gittest.ClassData;
import com.example.gittest.db.ClassContract.ClassEntry;
import com.example.gittest.db.CourseContract.CourseEntry;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CourseData.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE CourseDetails(id INTEGER PRIMARY KEY AUTOINCREMENT, day TEXT, time TEXT, capacity TEXT, duration TEXT, price TEXT, type TEXT, description TEXT)");
        db.execSQL(CourseContract.SQL_CREATE_TABLE);
        db.execSQL(ClassContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS CourseDetails");
        db.execSQL(CourseContract.SQL_DELETE_ENTRIES);
        db.execSQL(ClassContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    // Method to insert data with individual parameters
    public boolean insertCourseData(String day, String time, String capacity, String duration, String price, String type, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CourseEntry.COLUMN_NAME_DAY, day);
        contentValues.put(CourseEntry.COLUMN_NAME_TIME, time);
        contentValues.put(CourseEntry.COLUMN_NAME_CAPACITY, capacity);
        contentValues.put(CourseEntry.COLUMN_NAME_DURATION, duration);
        contentValues.put(CourseEntry.COLUMN_NAME_PRICE, price);
        contentValues.put(CourseEntry.COLUMN_NAME_TYPE, type);
        contentValues.put(CourseEntry.COLUMN_NAME_DESCRIPTION, description);

        long result = db.insert(CourseEntry.TABLE_NAME, null, contentValues);
        db.close();
        return result != -1;
    }

    // Method to insert data with a YogaEntry object
    public boolean insertCourseData(Course entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CourseEntry.COLUMN_NAME_DAY, entry.getDay());
        contentValues.put(CourseEntry.COLUMN_NAME_TIME, entry.getTime());
        contentValues.put(CourseEntry.COLUMN_NAME_CAPACITY, entry.getCapacity());
        contentValues.put(CourseEntry.COLUMN_NAME_DURATION, entry.getDuration());
        contentValues.put(CourseEntry.COLUMN_NAME_PRICE, entry.getPrice());
        contentValues.put(CourseEntry.COLUMN_NAME_TYPE, entry.getType());
        contentValues.put(CourseEntry.COLUMN_NAME_DESCRIPTION, entry.getDescription());

        long result = db.insert(CourseEntry.TABLE_NAME, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean insertClassData(ClassData data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ClassEntry.COLUMN_NAME_TEACHER, data.getTeacher());
        contentValues.put(ClassEntry.COLUMN_NAME_DATE, data.getDate());
        contentValues.put(ClassEntry.COLUMN_NAME_COURSE_ID, data.getCourseId());
        long result = db.insert(ClassEntry.TABLE_NAME, null, contentValues);
        db.close();
        return result != -1;
    }

    // Method to delete data with YogaEntry object
    public boolean deleteCourseData(String entryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowId = db.delete(CourseEntry.TABLE_NAME, "id = ?", new String[]{entryId});
        return rowId != -1;
    }

    // Method to update a YogaEntry
    public boolean updateYogaEntry(Course entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("day", entry.getDay());
        values.put("time", entry.getTime());
        values.put("capacity", entry.getCapacity());
        values.put("duration", entry.getDuration());
        values.put("price", entry.getPrice());
        values.put("type", entry.getType());
        values.put("description", entry.getDescription());

        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(entry.getId())};

        int rowsAffected = db.update(CourseEntry.TABLE_NAME, values, whereClause, whereArgs);

        db.close();

        return rowsAffected > 0;
    }

    // Method to get a YogaEntry by ID
    public Course getEntryById(long entryId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                CourseEntry.TABLE_NAME,
                null,
                "id = ?",
                new String[]{String.valueOf(entryId)},
                null,
                null,
                null
        );

        Course entry = null;
        if (cursor != null && cursor.moveToFirst()) {
            entry = new Course(
                    cursor.getLong(cursor.getColumnIndex(CourseEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_DAY)),
                    cursor.getString(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_TIME)),
                    cursor.getInt(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_CAPACITY)),
                    cursor.getDouble(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_DURATION)),
                    cursor.getDouble(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_PRICE)),
                    cursor.getString(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_TYPE)),
                    cursor.getString(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_DESCRIPTION))
            );
            cursor.close();
        }

        return entry;
    }

    public List<Course> getCourseDAta() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CourseEntry.TABLE_NAME, null);
        List<Course> courseDataList = new ArrayList<>();
        while (cursor != null && cursor.moveToNext()) {
            Course course = new Course(
                    cursor.getLong(cursor.getColumnIndex(CourseEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_DAY)),
                    cursor.getString(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_TIME)),
                    cursor.getInt(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_CAPACITY)),
                    cursor.getDouble(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_DURATION)),
                    cursor.getDouble(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_PRICE)),
                    cursor.getString(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_TYPE)),
                    cursor.getString(cursor.getColumnIndex(CourseEntry.COLUMN_NAME_DESCRIPTION))
            );
            courseDataList.add(course);
        }
        return courseDataList;
    }

    public List<ClassData> getClassData(long courseId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + ClassEntry.TABLE_NAME + " WHERE " + ClassEntry.COLUMN_NAME_COURSE_ID + "=?",
                new String[]{"" + courseId}
        );
        List<ClassData> classDataList = new ArrayList<>();
        while (cursor != null && cursor.moveToNext()) {
            ClassData data = new ClassData(
                    cursor.getLong(cursor.getColumnIndex(ClassEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(ClassEntry.COLUMN_NAME_TEACHER)),
                    cursor.getString(cursor.getColumnIndex(ClassEntry.COLUMN_NAME_DATE)),
                    cursor.getLong(cursor.getColumnIndex(ClassEntry.COLUMN_NAME_COURSE_ID))
            );
            classDataList.add(data);
        }
        return classDataList;
    }
}
