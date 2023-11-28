package com.example.gittest;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PastEntries extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_entries);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the data from the database
        DB = new DBHelper(this);
        Cursor res = DB.getCourseData();

        // Set up RecyclerView Adapter
        if (res != null) {
            PastEntriesAdapter adapter = new PastEntriesAdapter(this, res);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DB != null) {
            DB.close(); // Close the database helper when the activity is destroyed
        }
    }

    private static class PastEntriesAdapter extends RecyclerView.Adapter<PastEntriesAdapter.ViewHolder> {
        private final Cursor cursor;

        public PastEntriesAdapter(PastEntries pastEntries, Cursor cursor) {
            this.cursor = cursor;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_past_entry, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (cursor.moveToPosition(position)) {
                // Customize this part based on your database structure
                String day = cursor.getString(cursor.getColumnIndex("day"));
                holder.textViewDay.setText("Day: " + day);

                String time = cursor.getString(cursor.getColumnIndex("time"));
                holder.textViewTime.setText("Time: " + time);

                String capacity = cursor.getString(cursor.getColumnIndex("capacity"));
                holder.textViewCapacity.setText("Capacity: " + capacity);

                String duration = cursor.getString(cursor.getColumnIndex("duration"));
                holder.textViewDuration.setText("Duration: " + duration);

                String price = cursor.getString(cursor.getColumnIndex("price"));
                holder.textViewPrice.setText("Price: " + price);

                String type = cursor.getString(cursor.getColumnIndex("type"));
                holder.textViewType.setText("Type: " + type);

                String description = cursor.getString(cursor.getColumnIndex("description"));
                holder.textViewDescription.setText("Description: " + description);
            }
        }

        @Override
        public int getItemCount() {
            return cursor != null ? cursor.getCount() : 0;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final TextView textViewDay;
            public final TextView textViewTime;
            public final TextView textViewCapacity;
            public final TextView textViewDuration;
            public final TextView textViewPrice;
            public final TextView textViewType;
            public final TextView textViewDescription;

            public ViewHolder(View view) {
                super(view);
                textViewDay = view.findViewById(R.id.textViewDay);
                textViewTime = view.findViewById(R.id.textViewTime);
                textViewCapacity = view.findViewById(R.id.textViewCapacity);
                textViewDuration = view.findViewById(R.id.textViewDuration);
                textViewPrice = view.findViewById(R.id.textViewPrice);
                textViewType = view.findViewById(R.id.textViewType);
                textViewDescription = view.findViewById(R.id.textViewDescription);
            }
        }
    }
}
