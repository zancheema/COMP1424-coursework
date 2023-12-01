package com.example.gittest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {
    private final List<ClassData> classDataList;

    public ClassListAdapter(List<ClassData> classDataList) {
        this.classDataList = classDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassData classData = classDataList.get(position);
        holder.bind(classData);
    }

    @Override
    public int getItemCount() {
        return classDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTeacher;
        private final TextView tvDate;
        private final TextView tvComments;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTeacher = itemView.findViewById(R.id.tvTeacher);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvComments = itemView.findViewById(R.id.tvComments);
        }

        public void bind(ClassData data) {
            tvTeacher.setText("Teacher: " + data.getTeacher());
            tvDate.setText("Date: " + data.getDate());
            tvComments.setText("Additional Comments: " + data.getComments());
        }
    }
}
