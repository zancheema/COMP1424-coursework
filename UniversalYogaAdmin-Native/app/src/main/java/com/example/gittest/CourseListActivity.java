package com.example.gittest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gittest.db.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvNoCourses;
    private DBHelper DB;
    private EntryViewModel viewModel;
    private FloatingActionButton fabAddCourse;
    private CourseListAdapter courseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseListAdapter = new CourseListAdapter(this, listeners);
        recyclerView.setAdapter(courseListAdapter);

        fabAddCourse = findViewById(R.id.fabAddCourse);
        tvNoCourses = findViewById(R.id.tvNoCourses);

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
        DB = new DBHelper(this);
        List<Course> courseDataList = DB.getCourseDAta();
        tvNoCourses.setVisibility(courseDataList.isEmpty() ? View.VISIBLE : View.INVISIBLE);
        courseListAdapter.setData(courseDataList);
    }

    private CourseListAdapter.CourseListAdapterOnClickListeners listeners = new CourseListAdapter.CourseListAdapterOnClickListeners() {
        @Override
        public void onUpdate(long courseId) {
            Intent updateIntent = new Intent(CourseListActivity.this, UpdateDetailsActivity.class);
            updateIntent.putExtra(UpdateDetailsActivity.ENTRY_ID, courseId);
            CourseListActivity.this.startActivity(updateIntent);
        }

        @Override
        public void onDelete(long courseId) {
            viewModel.deleteEntry(courseId);
            Toast.makeText(CourseListActivity.this, "Entry Deleted successfully.", Toast.LENGTH_SHORT).show();
            loadEntries();
        }

        @Override
        public void onClick(long courseId) {
            Intent intent = new Intent(CourseListActivity.this, ClassListActivity.class);
            intent.putExtra(ClassListActivity.COURSE_ID, courseId);
            CourseListActivity.this.startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_upload) {
            upload();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void upload() {
        // upload
        Toast.makeText(this, "Upload Successful.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DB != null) {
            DB.close(); // Close the database helper when the activity is destroyed
        }
    }
}
