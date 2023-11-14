package com.example.activityyogacourse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class YogaCourseActivity extends AppCompatActivity {

    // Declare variables for UI elements
    private EditText dayEditText, timeEditText, capacityEditText, durationEditText, priceEditText, typeEditText, descriptionEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_course);

        // Initialize UI elements
        dayEditText = findViewById(R.id.editTextDay);
        timeEditText = findViewById(R.id.editTextTime);
        capacityEditText = findViewById(R.id.editTextCapacity);
        durationEditText = findViewById(R.id.editTextDuration);
        priceEditText = findViewById(R.id.editTextPrice);
        typeEditText = findViewById(R.id.editTextType);
        descriptionEditText = findViewById(R.id.editTextDescription);
        submitButton = findViewById(R.id.buttonSubmit);

        // Set click listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate and process the input
                if (validateInput()) {
                    displayConfirmation();
                }
            }
        });
    }

    private boolean validateInput() {
        // Check if required fields are not empty
        if (dayEditText.getText().toString().trim().isEmpty() ||
                timeEditText.getText().toString().trim().isEmpty() ||
                capacityEditText.getText().toString().trim().isEmpty() ||
                durationEditText.getText().toString().trim().isEmpty() ||
                priceEditText.getText().toString().trim().isEmpty() ||
                typeEditText.getText().toString().trim().isEmpty()) {
            // Display error message
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Additional validation logic can be added here

        return true;
    }

    private void displayConfirmation() {
        // Build confirmation message
        StringBuilder confirmationMessage = new StringBuilder("Course Details:\n");
        confirmationMessage.append("Day: ").append(dayEditText.getText().toString()).append("\n");
        confirmationMessage.append("Time: ").append(timeEditText.getText().toString()).append("\n");
        confirmationMessage.append("Capacity: ").append(capacityEditText.getText().toString()).append("\n");
        confirmationMessage.append("Duration: ").append(durationEditText.getText().toString()).append("\n");
        confirmationMessage.append("Price: ").append(priceEditText.getText().toString()).append("\n");
        confirmationMessage.append("Type: ").append(typeEditText.getText().toString()).append("\n");

        // Check if description is provided
        if (!descriptionEditText.getText().toString().trim().isEmpty()) {
            confirmationMessage.append("Description: ").append(descriptionEditText.getText().toString()).append("\n");
        }

        // Display confirmation message
        TextView confirmationTextView = new TextView(this);
        confirmationTextView.setText(confirmationMessage.toString());
        setContentView(confirmationTextView);

        // Add "Edit" button to go back and change details
        Button editButton = new Button(this);
        editButton.setText("Edit Details");
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_yoga_course); // Set the original layout
            }
        });

        addContentView(editButton, submitButton.getLayoutParams());
    }
}
