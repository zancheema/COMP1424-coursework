package com.example.gittest;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class UpdateDetailsActivity extends AppCompatActivity {
    private final DBHelper DB = new DBHelper(this);
    private EditText editDay, editTime, editCapacity, editDuration, editPrice, editType, editDescription;
    private EntryViewModel viewModel;
    private long entryId; // The ID of the entry to be updated

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        entryId = getIntent().getLongExtra("entryId", -1);

        viewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        // Initialize EditText fields
        editDay = findViewById(R.id.editTextDay);
        editTime = findViewById(R.id.editTextTime);
        editCapacity = findViewById(R.id.editCapacity);
        editDuration = findViewById(R.id.editDuration);
        editPrice = findViewById(R.id.editPrice);
        editType = findViewById(R.id.editType);
        editDescription = findViewById(R.id.editDescription);

        // Retrieve the entry ID from the intent
        entryId = getIntent().getLongExtra("entryId", -1);

        // If entryId is -1, handle the error or finish the activity
        if (entryId == -1) {
            Toast.makeText(this, "Error: Entry ID not provided", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Retrieve the existing entry details based on the entryId
        YogaEntry existingEntry = retrieveEntryDetails(entryId);

        // Populate the EditText fields with existing entry details
        populateFields(existingEntry);
    }

    private YogaEntry retrieveEntryDetails(long entryId) {
        // Use your DBHelper class or any other database-related code to retrieve entry details
        // Replace the following line with actual database query logic

        // For now, returning a dummy entry
        return DB.getEntryById(entryId);
    }

    private void populateFields(YogaEntry entry) {
        // Set the values of EditText fields based on the existing entry
        editDay.setText(entry.getDay());
        editTime.setText(entry.getTime());
        editCapacity.setText(String.valueOf(entry.getCapacity()));
        editDuration.setText(entry.getDuration());
        editPrice.setText(entry.getPrice());
        editType.setText(entry.getType());
        editDescription.setText(entry.getDescription());
    }

    public void onUpdateClick(View view) {
        // Get updated input values
        String day = editDay.getText().toString().trim();
        String time = editTime.getText().toString().trim();
        String capacity = editCapacity.getText().toString().trim();
        String duration = editDuration.getText().toString().trim();
        String price = editPrice.getText().toString().trim();
        String type = editType.getText().toString().trim();
        String description = editDescription.getText().toString().trim();

        // Check for required fields
        if (day.isEmpty() || time.isEmpty() || capacity.isEmpty() || duration.isEmpty() || price.isEmpty() || type.isEmpty()) {
            // Display an error message
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int capacityValue = Integer.parseInt(capacity);

            // Create an updated entry object
            YogaEntry updatedEntry = new YogaEntry(entryId, day, time, capacityValue, duration, price, type, description);

            // Update the entry in the database using ViewModel
            viewModel.updateEntry(updatedEntry);

            // Optionally, you can also update the entry directly in the database
            // UpdateEntryDatabaseHelper.updateEntry(updatedEntry);

            // Provide feedback to the user
            Toast.makeText(this, "Entry updated successfully", Toast.LENGTH_SHORT).show();

            // Finish the activity or navigate back to the main screen
            finish();
        } catch (NumberFormatException e) {
            // Handle the case where capacity is not a valid integer
            Toast.makeText(this, "Capacity should be a valid number", Toast.LENGTH_SHORT).show();
        }
    }
}
