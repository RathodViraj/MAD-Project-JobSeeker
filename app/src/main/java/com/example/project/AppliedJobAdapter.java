package com.example.project;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AppliedJobAdapter extends RecyclerView.Adapter<AppliedJobAdapter.AppliedViewHolder> {

    private List<AppliedJob> appliedJobs;

    public AppliedJobAdapter(List<AppliedJob> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }

    @NonNull
    @Override
    public AppliedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applied_job_item, parent, false);
        return new AppliedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppliedViewHolder holder, int position) {
        AppliedJob appliedJob = appliedJobs.get(position);
        Job job = appliedJob.getJob();
        
        holder.title.setText(job.getTitle());
        holder.company.setText(job.getCompany());
        
        holder.buttonViewResume.setOnClickListener(v -> {
            Uri resumeUri = appliedJob.getResumeUri();
            if (resumeUri != null) {
                try {
                    // Start an intent to view the PDF
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    // Use FLAG_GRANT_READ_URI_PERMISSION so the PDF viewer can read the file
                    intent.setDataAndType(resumeUri, "application/pdf");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    // FLAG_ACTIVITY_NO_HISTORY ensures the viewer doesn't stay in the stack
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    v.getContext().startActivity(intent);
                } catch (Exception e) {
                    // Fallback to a broader ACTION_VIEW if setType fails
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, resumeUri);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        v.getContext().startActivity(intent);
                    } catch (Exception ex) {
                        Toast.makeText(v.getContext(), "Error opening file. Make sure a PDF viewer is installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(v.getContext(), "No resume found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return appliedJobs.size();
    }

    public static class AppliedViewHolder extends RecyclerView.ViewHolder {
        TextView title, company;
        Button buttonViewResume;

        public AppliedViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewAppliedTitle);
            company = itemView.findViewById(R.id.textViewAppliedCompany);
            buttonViewResume = itemView.findViewById(R.id.buttonViewResume);
        }
    }
}
