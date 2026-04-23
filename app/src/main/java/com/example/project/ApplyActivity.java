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
                        
                        // TAKE PERSISTABLE PERMISSION
                        try {
                            getContentResolver().takePersistableUriPermission(selectedPdfUri, 
                                    Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        } catch (SecurityException e) {
                            // Some providers don't support persistable permissions
                        }
                        
                        textViewFileName.setText("File selected: " + selectedPdfUri.getLastPathSegment());
                    }
                }
        );

        buttonUploadResume.setOnClickListener(v -> {
            // Using ACTION_OPEN_DOCUMENT for better URI persistence
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/pdf");
            filePickerLauncher.launch(intent);
        });

        buttonSubmit.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            if (name.isEmpty() || selectedPdfUri == null) {
                Toast.makeText(this, "Please fill required fields and upload resume", Toast.LENGTH_SHORT).show();
            } else {
                if (job != null) {
                    UserSession.applyForJob(job, selectedPdfUri);
                }
                Toast.makeText(this, "Application Submitted!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
