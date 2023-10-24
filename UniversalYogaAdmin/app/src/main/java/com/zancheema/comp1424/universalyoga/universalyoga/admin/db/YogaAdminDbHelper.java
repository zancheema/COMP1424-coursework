package com.zancheema.comp1424.universalyoga.universalyoga.admin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class YogaAdminDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "YogaAdmin.db";

    public YogaAdminDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE course_detail(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "day_of_week VARCHAR, " +
                "time VARCHAR, " +
                "capacity INTEGER, " +
                "duration INTEGER, " +
                "price_per_class REAL, " +
                "type_of_class VARCHAR, " +
                "description TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS course_detail;");
    }
}
