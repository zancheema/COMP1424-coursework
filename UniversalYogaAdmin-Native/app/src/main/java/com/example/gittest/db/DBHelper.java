package com.example.gittest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gittest.Course;
import com.example.gittest.db.CourseContract.CourseEntry;

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

        long result = db.insert("CourseDetails", null, contentValues);
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

        long result = db.insert("CourseDetails", null, contentValues);
        db.close();
        return result != -1;
    }

    // Method to delete data with YogaEntry object
    public boolean deleteCourseData(String entryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowId = db.delete("CourseDetails", "id = ?", new String[]{entryId});
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

        int rowsAffected = db.update("CourseDetails", values, whereClause, whereArgs);

        db.close();

        return rowsAffected > 0;
    }

    // Method to get a YogaEntry by ID
    public Course getEntryById(long entryId) {
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

    // Method to get all course data as a Cursor
    public Cursor getCourseData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM CourseDetails", null);
    }
}
