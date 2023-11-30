package com.example.gittest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gittest.db.DBHelper;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddCourseActivity extends AppCompatActivity {
//    private EditText dayEditText;
    private Spinner spinnerDays;
    private EditText timeEditText;
    private EditText capacityEditText;
    private EditText durationEditText;
    private EditText priceEditText;
    private EditText typeEditText;
    private EditText descriptionEditText;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

//        dayEditText = findViewById(R.id.editTextDay);
        spinnerDays = findViewById(R.id.spinnerDays);
        timeEditText = findViewById(R.id.editTextTime);
        capacityEditText = findViewById(R.id.editCapacity);
        durationEditText = findViewById(R.id.editDuration);
        priceEditText = findViewById(R.id.editPrice);
        typeEditText = findViewById(R.id.editType);
        descriptionEditText = findViewById(R.id.editDescription);

        timeEditText.setOnClickListener(this::selectTime);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.days_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDays.setAdapter(adapter);

        Button submitButton = findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(this::onSubmitClick);


        DB = new DBHelper(this);
    }

    private void selectTime(View view) {
        LocalTime now = LocalTime.now();
        TimePickerDialog dialog = new TimePickerDialog(this, (timePicker, hour, minute) -> {
            timeEditText.setText(hour + ":" + minute);
        }, now.getHour(), now.getMinute(), true);
        dialog.show();
    }

    public void onSubmitClick(View view) {
        if (validateAndSubmit()) {
            Toast.makeText(this, "Course added successfully.", Toast.LENGTH_SHORT)
                    .show();
            finish();
        }
    }

    private String getConfirmationMessage() {
        // Create and return the confirmation message based on your logic
        // For example:
        return "Course details:\n" +
                "Day: " + spinnerDays.getSelectedItem().toString().trim() + "\n" +
                "Time: " + timeEditText.getText().toString().trim() + "\n" +
                "Capacity: " + capacityEditText.getText().toString().trim() + "\n" +
                "Duration: " + durationEditText.getText().toString().trim() + "\n" +
                "Price: " + priceEditText.getText().toString().trim() + "\n" +
                "Type: " + typeEditText.getText().toString().trim() + "\n" +
                "Description: " + descriptionEditText.getText().toString().trim();
    }

    private boolean validateAndSubmit() {
        String day = spinnerDays.getSelectedItem().toString().trim();
        String time = timeEditText.getText().toString().trim();
        String capacity = capacityEditText.getText().toString().trim();
        String duration = durationEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();
        String type = typeEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        if (day.isEmpty() || time.isEmpty() || capacity.isEmpty() ||
                duration.isEmpty() || price.isEmpty() || type.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Insert data into the database
        boolean isInserted = DB.insertCourseData(day, time, capacity, duration, price, type, description);

        // Return the result of the insertion
        return isInserted;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle the Up button press (if needed)
                onBackPressed(); // or finish()
                return true;
            // Handle other menu items if needed
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
