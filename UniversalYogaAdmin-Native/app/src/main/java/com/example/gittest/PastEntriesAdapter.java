package com.example.gittest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class PastEntriesAdapter extends RecyclerView.Adapter<PastEntriesAdapter.ViewHolder> {
    private static final String TAG = "PastEntriesAdapter";

    private final Cursor cursor;
    private final Context context; // Add this to store the context

    public PastEntriesAdapter(Context context, Cursor cursor) {
        this.context = context;
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
            long entryId = cursor.getLong(0);

            // Customize this part based on your database structure
            String day = cursor.getString(1); // Assuming day is stored in the first column
            holder.textViewDay.setText("Day: " + day);

            String time = cursor.getString(2); // Assuming time is stored in the second column
            holder.textViewTime.setText("Time: " + time);

            String capacity = cursor.getString(3); // Assuming capacity is stored in the third column
            holder.textViewCapacity.setText("Capacity: " + capacity);

            String duration = cursor.getString(4); // Assuming duration is stored in the fourth column
            holder.textViewDuration.setText("Duration: " + duration);

            String price = cursor.getString(5); // Assuming price is stored in the fifth column
            holder.textViewPrice.setText("Price: " + price);

            String type = cursor.getString(6); // Assuming type is stored in the sixth column
            holder.textViewType.setText("Type: " + type);

            String description = cursor.getString(7); // Assuming description is stored in the seventh column
            holder.textViewDescription.setText("Description: " + description);

            // Add the following lines to access btnUpdate from your layout
            holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchUpdateActivity(entryId);
                }
            });
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
        public final Button btnUpdate;

        public ViewHolder(View view) {
            super(view);
            textViewDay = view.findViewById(R.id.textViewDay);
            textViewTime = view.findViewById(R.id.textViewTime);
            textViewCapacity = view.findViewById(R.id.textViewCapacity);
            textViewDuration = view.findViewById(R.id.textViewDuration);
            textViewPrice = view.findViewById(R.id.textViewPrice);
            textViewType = view.findViewById(R.id.textViewType);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            btnUpdate = view.findViewById(R.id.btnEditDetails); // Replace R.id.btnUpdate with the actual ID of your button
        }
    }

    private void launchUpdateActivity(long entryId) {
        // You can create an Intent to launch an update activity and pass the details as extras
        Intent updateIntent = new Intent(context, UpdateDetailsActivity.class);
        Log.d(TAG, "launchUpdateActivity: entryId: " + entryId);
        updateIntent.putExtra(UpdateDetailsActivity.ENTRY_ID, entryId);

        context.startActivity(updateIntent);
    }
}
