package com.example.gittest;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PastEntries extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DBHelper DB;
    private EntryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_entries);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(EntryViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Call this in onStart so that entries will be updated once we navigate
        // back after updating an entry
        loadEntries();
    }

    private void loadEntries() {
        // Get the data from the database
        DB = new DBHelper(this);
        Cursor res = DB.getCourseData();

        // Set up RecyclerView Adapter
        if (res != null) {
            PastEntriesAdapter adapter = new PastEntriesAdapter(this, res, listeners);
            recyclerView.setAdapter(adapter);
        }
    }

    private PastEntriesAdapter.PastEntriesAdapterOnClickListeners listeners = new PastEntriesAdapter.PastEntriesAdapterOnClickListeners() {
        @Override
        public void onDelete(long entryId) {
            viewModel.deleteEntry(entryId);
            Toast.makeText(PastEntries.this, "Entry Deleted successfully.", Toast.LENGTH_SHORT).show();
            loadEntries();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DB != null) {
            DB.close(); // Close the database helper when the activity is destroyed
        }
    }
}
