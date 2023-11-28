package com.example.gittest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class PastEntriesAdapter extends RecyclerView.Adapter<PastEntriesAdapter.ViewHolder> {
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
            // Customize this part based on your database structure
            String day = cursor.getString(0); // Assuming day is stored in the first column
            holder.textViewDay.setText("Day: " + day);

            String time = cursor.getString(1); // Assuming time is stored in the second column
            holder.textViewTime.setText("Time: " + time);

            String capacity = cursor.getString(2); // Assuming capacity is stored in the third column
            holder.textViewCapacity.setText("Capacity: " + capacity);

            String duration = cursor.getString(3); // Assuming duration is stored in the fourth column
            holder.textViewDuration.setText("Duration: " + duration);

            String price = cursor.getString(4); // Assuming price is stored in the fifth column
            holder.textViewPrice.setText("Price: " + price);

            String type = cursor.getString(5); // Assuming type is stored in the sixth column
            holder.textViewType.setText("Type: " + type);

            String description = cursor.getString(6); // Assuming description is stored in the seventh column
            holder.textViewDescription.setText("Description: " + description);

            // Add the following lines to access btnUpdate from your layout
            holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the data associated with the clicked item
                    Toast.makeText(v.getContext(), "Update button clicked.", 2000L).show();
                    String day = cursor.getString(0);
                    String time = cursor.getString(1);
                    String capacity = cursor.getString(2);
                    String duration = cursor.getString(3);
                    String price = cursor.getString(4);
                    String type = cursor.getString(5);
                    String description = cursor.getString(6);

                    // Launch an activity or dialog to update details
                    launchUpdateActivity(day, time, capacity, duration, price, type, description);
                    Intent updateIntent = new Intent(context, UpdateDetailsActivity.class);
                    updateIntent.putExtra("day", day);
                    updateIntent.putExtra("time", time);
                    updateIntent.putExtra("capacity", capacity);
                    updateIntent.putExtra("duration", duration);
                    updateIntent.putExtra("price", price);
                    updateIntent.putExtra("type", type);
                    updateIntent.putExtra("description", description);

                    context.startActivity(updateIntent);
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
            btnUpdate = view.findViewById(R.id.EditDeitailsdbtn); // Replace R.id.btnUpdate with the actual ID of your button
        }
    }

    private void launchUpdateActivity(String day, String time, String capacity, String duration,
                                      String price, String type, String description) {
        // You can create an Intent to launch an update activity and pass the details as extras
        Intent updateIntent = new Intent(context, UpdateDetailsActivity.class);
        updateIntent.putExtra("day", day);
        updateIntent.putExtra("time", time);
        updateIntent.putExtra("capacity", capacity);
        updateIntent.putExtra("duration", duration);
        updateIntent.putExtra("price", price);
        updateIntent.putExtra("type", type);
        updateIntent.putExtra("description", description);

        context.startActivity(updateIntent);
    }
}
