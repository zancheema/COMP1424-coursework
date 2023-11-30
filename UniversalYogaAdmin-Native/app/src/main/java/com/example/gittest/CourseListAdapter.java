package com.example.gittest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {
    public interface CourseListAdapterOnClickListeners {
        void onUpdate(long courseId);
        void onDelete(long courseId);
        void onClick(long courseId);
    }

    private static final String TAG = "CourseListAdapter";

    private final List<Course> courseDataList = new ArrayList<>();
    private final Context context; // Add this to store the context
    private final CourseListAdapterOnClickListeners listeners;

    public CourseListAdapter(Context context, CourseListAdapterOnClickListeners listeners) {
        this.context = context;
        this.listeners = listeners;
    }

    public void setData(List<Course> newData) {
        courseDataList.clear();
        courseDataList.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view, listeners);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Course course = courseDataList.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courseDataList.size();
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
        public final Button btnDeleteEntry;

        private final CourseListAdapterOnClickListeners listeners;

        public ViewHolder(View view, CourseListAdapterOnClickListeners listeners) {
            super(view);
            textViewDay = view.findViewById(R.id.textViewDay);
            textViewTime = view.findViewById(R.id.textViewTime);
            textViewCapacity = view.findViewById(R.id.textViewCapacity);
            textViewDuration = view.findViewById(R.id.textViewDuration);
            textViewPrice = view.findViewById(R.id.textViewPrice);
            textViewType = view.findViewById(R.id.textViewType);
            textViewDescription = view.findViewById(R.id.textViewDescription);
            btnUpdate = view.findViewById(R.id.btnEditDetails); // Replace R.id.btnUpdate with the actual ID of your button
            btnDeleteEntry = view.findViewById(R.id.btnDeleteEntry);
            this.listeners = listeners;
        }

        public void bind(Course data) {
            long courseId = data.getId();

            textViewDay.setText("Day: " + data.getDay());
            textViewTime.setText("Time: " + data.getTime());
            textViewCapacity.setText("Capacity: " + data.getCapacity());
            textViewDuration.setText("Duration: " + data.getDuration());
            textViewPrice.setText("Price: " + data.getPrice());
            textViewType.setText("Type: " + data.getType());
            textViewDescription.setText("Description: " + data.getDescription());

            btnUpdate.setOnClickListener(v -> listeners.onUpdate(courseId));
            btnDeleteEntry.setOnClickListener(v -> listeners.onDelete(courseId));
            itemView.setOnClickListener(v -> listeners.onClick(courseId));
        }
    }
}
