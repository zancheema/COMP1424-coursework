package com.example.gittest;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText dayEditText;
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
        setContentView(R.layout.activity_main);

        dayEditText = findViewById(R.id.editTextDay);
        timeEditText = findViewById(R.id.editTextTime);
        capacityEditText = findViewById(R.id.editCapacity);
        durationEditText = findViewById(R.id.editDuration);
        priceEditText = findViewById(R.id.editPrice);
        typeEditText = findViewById(R.id.editType);
        descriptionEditText = findViewById(R.id.editDescription);

        Button submitButton = findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitClick(v);
            }
        });

        Button viewEntryButton = findViewById(R.id.buttonViewEntry);
        viewEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewEntryClick(v);
            }
        });

        DB = new DBHelper(this);
    }

    public void onSubmitClick(View view) {
        if (validateAndSubmit()) {
            // Start the ConfirmationActivity only if validation is successful
            Intent confirmationIntent = new Intent(MainActivity.this, ConfirmationActivity.class);
            confirmationIntent.putExtra("confirmationMessage", getConfirmationMessage());
            startActivity(confirmationIntent);

        }
    }

    private String getConfirmationMessage() {
        // Create and return the confirmation message based on your logic
        // For example:
        return "Course details:\n" +
                "Day: " + dayEditText.getText().toString().trim() + "\n" +
                "Time: " + timeEditText.getText().toString().trim() + "\n" +
                "Capacity: " + capacityEditText.getText().toString().trim() + "\n" +
                "Duration: " + durationEditText.getText().toString().trim() + "\n" +
                "Price: " + priceEditText.getText().toString().trim() + "\n" +
                "Type: " + typeEditText.getText().toString().trim() + "\n" +
                "Description: " + descriptionEditText.getText().toString().trim();
    }

    private boolean validateAndSubmit() {
        String day = dayEditText.getText().toString().trim();
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

    private void onViewEntryClick(View view) {
        // Start the PastEntriesActivity
        startActivity(new Intent(MainActivity.this, PastEntries.class));
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
