package com.example.gittest.db;

import android.provider.BaseColumns;

public class CourseContract {
    public static class CourseEntry implements BaseColumns {
        public static final String TABLE_NAME = "courses";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_DAY = "day";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_CAPACITY = "capacity";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + CourseEntry.TABLE_NAME + "(" +
            CourseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CourseEntry.COLUMN_NAME_DAY + " TEXT, " +
            CourseEntry.COLUMN_NAME_TIME + " TEXT, " +
            CourseEntry.COLUMN_NAME_CAPACITY + " INTEGER, " +
            CourseEntry.COLUMN_NAME_DURATION + " INTEGER, " +
            CourseEntry.COLUMN_NAME_PRICE + " FLOAT, " +
            CourseEntry.COLUMN_NAME_TYPE + " TEXT, " +
            CourseEntry.COLUMN_NAME_DESCRIPTION + " TEXT);";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CourseEntry.TABLE_NAME;
}
