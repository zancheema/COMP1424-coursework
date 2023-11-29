package com.example.gittest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gittest.db.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CourseListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DBHelper DB;
    private EntryViewModel viewModel;
    private FloatingActionButton fabAddCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fabAddCourse = findViewById(R.id.fabAddCourse);

        viewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        fabAddCourse.setOnClickListener(this::addCourse);
    }

    private void addCourse(View view) {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);
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
            Toast.makeText(CourseListActivity.this, "Entry Deleted successfully.", Toast.LENGTH_SHORT).show();
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
