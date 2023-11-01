package com.zancheema.comp1424.universalyoga.universalyoga.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button submitButton = findViewById(R.id.submitButton);
        EditText etDate = findViewById(R.id.editTextDate);
        EditText etTeacher = findViewById(R.id.editTextTeacher);
        EditText etComments = findViewById(R.id.editTextComments);

        submitButton.setOnClickListener(v -> {
            String date = etDate.getText().toString();
            String teacher = etTeacher.getText().toString();
            String comments = etComments.getText().toString();

            // save it in database
        });
    }
}