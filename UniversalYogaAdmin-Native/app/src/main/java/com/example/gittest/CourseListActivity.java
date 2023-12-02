package com.example.gittest;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
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

import com.example.gittest.api.APIClient;
import com.example.gittest.api.APIInterface;
import com.example.gittest.api.ClassesPayload;
import com.example.gittest.api.ClassesPostResponse;
import com.example.gittest.db.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseListActivity extends AppCompatActivity {
    private static final String TAG = "CourseListActivity";

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
        List<Course> courseDataList = DB.getCourseDataList();
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
            new AlertDialog.Builder(CourseListActivity.this)
                    .setTitle("Are you sure?")
                    .setMessage("The course and all associated classes will be permanently deleted.")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        viewModel.deleteEntry(courseId);
                        Toast.makeText(CourseListActivity.this, "Entry Deleted successfully.", Toast.LENGTH_SHORT).show();
                        loadEntries();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
//                            .setNegativeButton("")
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
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        ClassesPayload payload = new ClassesPayload();
        payload.setUserId("group_29");
        List<ClassesPayload.Detail> detailList = new ArrayList<>();
        List<Course> courseDataList = DB.getCourseDataList();
        for (Course course : courseDataList) {
            ClassesPayload.Detail detail = new ClassesPayload.Detail();
            detail.setDayOfWeek(course.getDay());
            detail.setTimeOfDay(course.getTime());

            List<ClassData> classDataList = DB.getClassDataList(course.getId());
            List<ClassesPayload.ClassInfo> classList = new ArrayList<>();
            for (ClassData classData : classDataList) {
                ClassesPayload.ClassInfo classInfo = new ClassesPayload.ClassInfo(classData.getDate(), classData.getTeacher());
                classList.add(classInfo);
            }
            detail.setClassList(classList);
            detailList.add(detail);
        }
        payload.setDetailList(detailList);
        Log.d(TAG, "upload: " + payload);

        apiInterface.submitClasses(payload, "Submit")
                        .enqueue(new Callback<ClassesPostResponse>() {
                            @Override
                            public void onResponse(Call<ClassesPostResponse> call, Response<ClassesPostResponse> response) {
                                Toast.makeText(CourseListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ClassesPostResponse> call, Throwable t) {
                                Toast.makeText(CourseListActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DB != null) {
            DB.close(); // Close the database helper when the activity is destroyed
        }
    }
}
