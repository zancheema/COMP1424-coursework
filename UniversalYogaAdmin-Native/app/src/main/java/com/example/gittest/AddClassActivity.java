package com.example.gittest;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.example.gittest.db.DBHelper;

import java.time.LocalDate;

public class AddClassActivity extends AppCompatActivity {
    public static final String PARAM_COURSE_ID = "courseId";

    private long courseId;
    private EditText etTeacher;
    private EditText etDate;
    private Button btnSave;
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        courseId = getIntent().getLongExtra(PARAM_COURSE_ID, -1);
        if (courseId == -1) {
            Toast.makeText(this, "Invalid course.", Toast.LENGTH_LONG).show();
            finish();
        }

        etTeacher = findViewById(R.id.etTeacher);
        etDate = findViewById(R.id.etDate);
        btnSave = findViewById(R.id.btnSave);

        etDate.setOnClickListener(this::selectDate);
        btnSave.setOnClickListener(this::saveClass);
    }

    private void saveClass(View view) {
        String teacher = etTeacher.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        if (teacher.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "No field must be empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        DBHelper db = new DBHelper(this);
        new Thread(() -> {
            boolean success = db.insertClassData(new ClassData(0L, teacher, date, courseId));
            mainHandler.post(() -> {
                if (success) {
                    Toast.makeText(AddClassActivity.this, "Class created successfully.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddClassActivity.this, "Class creation failed.", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private void selectDate(View view) {
        LocalDate today = LocalDate.now();
        DatePickerDialog dialog = new DatePickerDialog(this, (datePicker, year, month, day) -> {
            etDate.setText(day + "/" + month + "/" + year);
        }, today.getYear(), today.getDayOfMonth(), today.getDayOfMonth());
        dialog.show();
    }
}