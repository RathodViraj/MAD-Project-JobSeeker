package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ApplyActivity extends AppCompatActivity {

    private EditText editTextName, editTextQualification, editTextCurrentCTC, editTextExpectedCTC, editTextNoticePeriod, editTextLocation;
    private Button buttonUploadResume, buttonSubmit;
    private TextView textViewFileName;
    private Uri selectedPdfUri = null;
    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        // Get the Job object to know which job we are applying for
        job = (Job) getIntent().getSerializableExtra("job_data");

        editTextName = findViewById(R.id.editTextName);
        editTextQualification = findViewById(R.id.editTextQualification);
        editTextCurrentCTC = findViewById(R.id.editTextCurrentCTC);
        editTextExpectedCTC = findViewById(R.id.editTextExpectedCTC);
        editTextNoticePeriod = findViewById(R.id.editTextNoticePeriod);
        editTextLocation = findViewById(R.id.editTextLocation);
        
        buttonUploadResume = findViewById(R.id.buttonUploadResume);
        buttonSubmit = findViewById(R.id.buttonSubmitApplication);
        textViewFileName = findViewById(R.id.textViewFileName);

        ActivityResultLauncher<Intent> filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        selectedPdfUri = result.getData().getData();
                        textViewFileName.setText("File selected: " + selectedPdfUri.getLastPathSegment());
                    }
                }
        );

        buttonUploadResume.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("application/pdf");
            filePickerLauncher.launch(intent);
        });

        buttonSubmit.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String qualification = editTextQualification.getText().toString().trim();
            String currentCTC = editTextCurrentCTC.getText().toString().trim();
            String expectedCTC = editTextExpectedCTC.getText().toString().trim();
            String noticePeriod = editTextNoticePeriod.getText().toString().trim();
            String location = editTextLocation.getText().toString().trim();

            // Basic Validation for all fields
            if (name.isEmpty()) {
                editTextName.setError("Name is required");
            } else if (qualification.isEmpty()) {
                editTextQualification.setError("Qualification is required");
            } else if (currentCTC.isEmpty()) {
                editTextCurrentCTC.setError("Current CTC is required");
            } else if (expectedCTC.isEmpty()) {
                editTextExpectedCTC.setError("Expected CTC is required");
            } else if (noticePeriod.isEmpty()) {
                editTextNoticePeriod.setError("Notice period is required");
            } else if (location.isEmpty()) {
                editTextLocation.setError("Location preference is required");
            } else if (selectedPdfUri == null) {
                Toast.makeText(this, "Please upload your resume", Toast.LENGTH_SHORT).show();
            } else {
                // Save the application status in the session
                if (job != null) {
                    UserSession.applyForJob(job.getTitle(), job.getCompany());
                }
                
                Toast.makeText(this, "Application Submitted Successfully!", Toast.LENGTH_LONG).show();
                
                // Return to MainActivity and refresh
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
