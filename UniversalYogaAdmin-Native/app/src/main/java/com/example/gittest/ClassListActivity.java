package com.example.gittest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gittest.db.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ClassListActivity extends AppCompatActivity {
    public static final String COURSE_ID = "courseId";

    private long courseId;

    private RecyclerView rcClassList;
    private TextView tvNoClasses;
    private FloatingActionButton fabAddClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        courseId = getIntent().getLongExtra(COURSE_ID, -1);
        if (courseId == -1) {
            Toast.makeText(this, "Invalid Course.", Toast.LENGTH_LONG).show();
            finish();
        }

        rcClassList = findViewById(R.id.rcClassList);
        tvNoClasses = findViewById(R.id.tvNoClasses);
        fabAddClass = findViewById(R.id.fabAddClass);

        fabAddClass.setOnClickListener(this::onAddClass);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadClasses();
    }

    private void onAddClass(View view) {
        Intent intent = new Intent(this, AddClassActivity.class);
        intent.putExtra(AddClassActivity.PARAM_COURSE_ID, courseId);
        startActivity(intent);
    }

    private void loadClasses() {
        rcClassList.setLayoutManager(new LinearLayoutManager(this));
        DBHelper db = new DBHelper(this);
        List<ClassData> classDataList = db.getClassData(courseId);
        if (classDataList.isEmpty()) {
            tvNoClasses.setVisibility(View.VISIBLE);
        } else {
            tvNoClasses.setVisibility(View.INVISIBLE);
            rcClassList.setAdapter(new ClassListAdapter(classDataList));
        }
    }
}