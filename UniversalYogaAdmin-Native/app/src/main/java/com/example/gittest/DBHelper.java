package com.example.gittest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CourseData.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE CourseDetails(id INTEGER PRIMARY KEY AUTOINCREMENT, day TEXT, time TEXT, capacity TEXT, duration TEXT, price TEXT, type TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CourseDetails");
        onCreate(db);
    }

    // Method to insert data with individual parameters
    public boolean insertCourseData(String day, String time, String capacity, String duration, String price, String type, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("day", day);
        contentValues.put("time", time);
        contentValues.put("capacity", capacity);
        contentValues.put("duration", duration);
        contentValues.put("price", price);
        contentValues.put("type", type);
        contentValues.put("description", description);

        long result = db.insert("CourseDetails", null, contentValues);
        db.close();
        return result != -1;
    }

    // Method to insert data with a YogaEntry object
    public boolean insertCourseData(YogaEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("day", entry.getDay());
        contentValues.put("time", entry.getTime());
        contentValues.put("capacity", entry.getCapacity());
        contentValues.put("duration", entry.getDuration());
        contentValues.put("price", entry.getPrice());
        contentValues.put("type", entry.getType());
        contentValues.put("description", entry.getDescription());

        long result = db.insert("CourseDetails", null, contentValues);
        db.close();
        return result != -1;
    }

    // Method to update a YogaEntry
    public boolean updateYogaEntry(YogaEntry entry) {
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

        int rowsAffected = db.update("CourseDetails", values, whereClause, whereArgs);

        db.close();

        return rowsAffected > 0;
    }

    // Method to get a YogaEntry by ID
    public YogaEntry getEntryById(long entryId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "CourseDetails",
                null,
                "id = ?",
                new String[]{String.valueOf(entryId)},
                null,
                null,
                null
        );

        YogaEntry entry = null;
        if (cursor != null && cursor.moveToFirst()) {
            entry = new YogaEntry(
                    cursor.getLong(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("day")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getInt(cursor.getColumnIndex("capacity")),
                    cursor.getString(cursor.getColumnIndex("duration")),
                    cursor.getString(cursor.getColumnIndex("price")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("description"))
            );
            cursor.close();
        }

        return entry;
    }

    // Method to get all course data as a Cursor
    public Cursor getCourseData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM CourseDetails", null);
    }
}
