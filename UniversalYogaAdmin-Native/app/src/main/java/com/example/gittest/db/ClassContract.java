package com.example.gittest.db;

import com.example.gittest.db.CourseContract.CourseEntry;

public class ClassContract {
    public static class ClassEntry {
        public static final String TABLE_NAME = "classes";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_TEACHER = "teacher";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_COURSE_ID = "course_id";
    }

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + ClassEntry.TABLE_NAME + "(" +
            ClassEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ClassEntry.COLUMN_NAME_TEACHER + " TEXT, " +
            ClassEntry.COLUMN_NAME_DATE + " TEXT, " +
            ClassEntry.COLUMN_NAME_COURSE_ID + " INTEGER, " +
            "FOREIGN KEY(" + ClassEntry.COLUMN_NAME_COURSE_ID + ")" + " REFERENCES " + CourseEntry.TABLE_NAME + "(" + CourseEntry._ID + ")" +
            ");";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ClassEntry.TABLE_NAME;
}
