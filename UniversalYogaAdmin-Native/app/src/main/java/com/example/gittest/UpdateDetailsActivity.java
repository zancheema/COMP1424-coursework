package com.example.gittest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gittest.db.DBHelper;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateDetailsActivity extends AppCompatActivity {
    private static final String TAG = "UpdateDetailsActivity";
    public static final String ENTRY_ID = "entryId";

    private final DBHelper DB = new DBHelper(this);
    private EditText editTime, editCapacity, editDuration, editPrice, editType, editDescription;
    private Spinner spinnerDays;
    private Button buttonUpdate;
    private EntryViewModel viewModel;
    private long entryId; // The ID of the entry to be updated

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
//        entryId = getIntent().getLongExtra("entryId", -1);
        entryId = getIntent().getLongExtra(ENTRY_ID, -1);
        Log.d(TAG, "onCreate: entryId: " + entryId);

        viewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        // Initialize EditText fields
//        editDay = findViewById(R.id.editTextDay);
        spinnerDays = findViewById(R.id.spinnerDays);
        editTime = findViewById(R.id.editTextTime);
        editCapacity = findViewById(R.id.editCapacity);
        editDuration = findViewById(R.id.editDuration);
        editPrice = findViewById(R.id.editPrice);
        editType = findViewById(R.id.editType);
        editDescription = findViewById(R.id.editDescription);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        // Retrieve the entry ID from the intent


        // If entryId is -1, handle the error or finish the activity
        if (entryId == -1) {
            Toast.makeText(this, "Error: Entry ID not provided", Toast.LENGTH_SHORT).show();
            finish();
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.days_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDays.setAdapter(adapter);

        // Retrieve the existing entry details based on the entryId
        Course existingEntry = retrieveEntryDetails(entryId);

        // Populate the EditText fields with existing entry details
        populateFields(existingEntry);

        // set onclick listeners
        editTime.setOnClickListener(this::selectTime);
        buttonUpdate.setOnClickListener(this::onUpdateClick);
    }



    private void selectTime(View view) {
        LocalTime now = LocalTime.now();
        TimePickerDialog dialog = new TimePickerDialog(this, (timePicker, hour, minute) -> {
            editTime.setText(hour + ":" + minute);
        }, now.getHour(), now.getMinute(), true);
        dialog.show();
    }

    private Course retrieveEntryDetails(long entryId) {
        // Use your DBHelper class or any other database-related code to retrieve entry details
        // Replace the following line with actual database query logic

        // For now, returning a dummy entry
        return DB.getEntryById(entryId);
    }

    private void populateFields(Course entry) {
        // Set the values of EditText fields based on the existing entry
//        editDay.setText(entry.getDay());
        spinnerDays.setSelection(getSelectedDayIndex(entry.getDay()));
        editTime.setText(entry.getTime());
        editCapacity.setText(String.valueOf(entry.getCapacity()));
        editDuration.setText(entry.getDuration() + "");
        editPrice.setText(entry.getPrice() + "");
        editType.setText(entry.getType());
        editDescription.setText(entry.getDescription());
    }

    private int getSelectedDayIndex(String day) {
        String[] days = getResources().getStringArray(R.array.days_array);
        for (int i = 0; i < days.length; i++) {
            if (days[i].equals(day)) return i;
        }
        return -1;
    }

    public void onUpdateClick(View view) {
        // Get updated input values
        String day = spinnerDays.getSelectedItem().toString().trim();
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
            Course updatedEntry = new Course(entryId, day, time, capacityValue, Double.parseDouble(duration), Double.parseDouble(price), type, description);

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
