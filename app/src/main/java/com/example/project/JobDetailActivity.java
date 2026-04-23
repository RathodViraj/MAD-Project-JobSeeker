package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class JobDetailActivity extends AppCompatActivity {

    private TextView textViewTitle, textViewCompany, textViewDescription, textViewDate, textViewLocation, textViewClosingDate;
    private Button buttonApply, buttonSave;
    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        job = (Job) getIntent().getSerializableExtra("job_data");

        textViewTitle = findViewById(R.id.textViewDetailTitle);
        textViewCompany = findViewById(R.id.textViewDetailCompany);
        textViewDescription = findViewById(R.id.textViewDetailDescription);
        textViewDate = findViewById(R.id.textViewDetailDate);
        textViewLocation = findViewById(R.id.textViewDetailLocation);
        textViewClosingDate = findViewById(R.id.textViewClosingDate);
        buttonApply = findViewById(R.id.buttonApply);
        buttonSave = findViewById(R.id.buttonSave);

        if (job != null) {
            textViewTitle.setText(job.getTitle());
            textViewCompany.setText(job.getCompany());
            textViewDescription.setText(job.getDescription());
            textViewDate.setText(job.getDatePosted());
            textViewLocation.setText(job.getLocation());
            textViewClosingDate.setText("Closing Date: " + job.getClosingDate());

            updateApplyButton();
            updateSaveButton();
        }

        buttonApply.setOnClickListener(v -> {
            if (job.isClosed()) {
                Toast.makeText(this, "This job is closed for applications.", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(JobDetailActivity.this, ApplyActivity.class);
            intent.putExtra("job_data", job);
            startActivity(intent);
        });

        buttonSave.setOnClickListener(v -> {
            if (UserSession.isJobSaved(job)) {
                UserSession.unsaveJob(job);
                Toast.makeText(this, "Job Removed from Saved", Toast.LENGTH_SHORT).show();
            } else {
                UserSession.saveJob(job);
                Toast.makeText(this, "Job Saved Successfully", Toast.LENGTH_SHORT).show();
            }
            updateSaveButton();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateApplyButton();
        updateSaveButton();
    }

    private void updateApplyButton() {
        if (job.isClosed()) {
            buttonApply.setText("Job Closed");
            buttonApply.setEnabled(false);
            buttonApply.setAlpha(0.5f);
        } else if (UserSession.isJobApplied(job.getTitle(), job.getCompany())) {
            buttonApply.setText("Applied");
            buttonApply.setEnabled(false);
            buttonApply.setAlpha(0.5f);
        } else {
            buttonApply.setText("Apply Now");
            buttonApply.setEnabled(true);
            buttonApply.setAlpha(1.0f);
        }
    }

    private void updateSaveButton() {
        if (UserSession.isJobSaved(job)) {
            buttonSave.setText("Unsave Job");
        } else {
            buttonSave.setText("Save Job");
        }
    }
}
