package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class JobDetailActivity extends AppCompatActivity {

    private TextView textViewTitle, textViewCompany, textViewDescription, textViewDate;
    private Button buttonApply;
    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        // Get the Job object passed via Intent
        job = (Job) getIntent().getSerializableExtra("job_data");

        textViewTitle = findViewById(R.id.textViewDetailTitle);
        textViewCompany = findViewById(R.id.textViewDetailCompany);
        textViewDescription = findViewById(R.id.textViewDetailDescription);
        textViewDate = findViewById(R.id.textViewDetailDate);
        buttonApply = findViewById(R.id.buttonApply);

        if (job != null) {
            textViewTitle.setText(job.getTitle());
            textViewCompany.setText(job.getCompany());
            textViewDescription.setText(job.getDescription());
            textViewDate.setText(job.getDatePosted());

            // Check if user has already applied for this job
            updateApplyButton();
        }

        buttonApply.setOnClickListener(v -> {
            Intent intent = new Intent(JobDetailActivity.this, ApplyActivity.class);
            intent.putExtra("job_data", job); // Pass job to ApplyActivity
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the button state if the user returns from ApplyActivity
        updateApplyButton();
    }

    private void updateApplyButton() {
        if (UserSession.isJobApplied(job.getTitle(), job.getCompany())) {
            buttonApply.setText("Applied");
            buttonApply.setEnabled(false);
            buttonApply.setAlpha(0.5f); // Make it look disabled
        } else {
            buttonApply.setText("Apply Now");
            buttonApply.setEnabled(true);
            buttonApply.setAlpha(1.0f);
        }
    }
}
