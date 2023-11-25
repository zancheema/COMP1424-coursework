// MainActivity.java
package com.example.activityyogacourse;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Launch YogaCourseActivity
        startActivity(new Intent(this, YogaCourseActivity.class));
        finish();
    }
}
