package com.zancheema.comp1424.universalyoga.universalyoga.admin;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;

public class EnterCourseDetailsActivity extends AppCompatActivity {
    LocalTime time = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_course_details);

        // set up day of week
        Spinner spinnerDayOfWeek = findViewById(R.id.spinnerDayOfWeek);
        String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        ArrayAdapter daysAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, days);
        spinnerDayOfWeek.setAdapter(daysAdapter);

        // set up course time
        Button btnCourseTime = findViewById(R.id.btnCourseTime);
        TimePickerDialog timepicker = new TimePickerDialog(this, (view, hour, min) -> {
            time = LocalTime.of(hour, min, 0);
            btnCourseTime.setText(hour + ":" + min);
        }, 1, 0, true);
        btnCourseTime.setOnClickListener(v -> {
            timepicker.show();
        });

        // set up type of class
        Spinner spinnerTypeOfClass = findViewById(R.id.spinnerTypeOfClass);
        String[] types = new String[]{"Flow Yoga", "Aerial Yoga", "Family Yoga"};
        ArrayAdapter typesAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types);
        spinnerTypeOfClass.setAdapter(typesAdapter);
    }
}