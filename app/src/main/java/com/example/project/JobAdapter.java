package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private List<Job> jobList;
    private List<Job> jobListFiltered;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Job job);
    }

    public JobAdapter(List<Job> jobList, OnItemClickListener listener) {
        this.jobList = jobList;
        this.jobListFiltered = new ArrayList<>(jobList);
        this.listener = listener;
    }

    public void filter(String query) {
        jobListFiltered.clear();
        if (query.isEmpty()) {
            jobListFiltered.addAll(jobList);
        } else {
            String lowerCaseQuery = query.toLowerCase().trim();
            for (Job job : jobList) {
                if (job.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                    job.getCompany().toLowerCase().contains(lowerCaseQuery) ||
                    job.getLocation().toLowerCase().contains(lowerCaseQuery)) {
                    jobListFiltered.add(job);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobListFiltered.get(position);
        holder.textViewTitle.setText(job.getTitle());
        holder.textViewCompany.setText(job.getCompany());
        holder.textViewLocation.setText(job.getLocation());
        holder.textViewDate.setText(job.getDatePosted());
        
        if (job.isClosed()) {
            holder.statusTag.setVisibility(View.VISIBLE);
            holder.itemView.setAlpha(0.6f);
        } else {
            holder.statusTag.setVisibility(View.GONE);
            holder.itemView.setAlpha(1.0f);
        }

        holder.itemView.setOnClickListener(v -> listener.onItemClick(job));
    }

    @Override
    public int getItemCount() {
        return jobListFiltered.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewCompany, textViewLocation, textViewDate, statusTag;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewJobTitle);
            textViewCompany = itemView.findViewById(R.id.textViewCompany);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            statusTag = itemView.findViewById(R.id.textViewStatus);
        }
    }
}
