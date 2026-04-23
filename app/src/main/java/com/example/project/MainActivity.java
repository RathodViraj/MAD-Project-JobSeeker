package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JobAdapter adapter;
    private List<Job> jobList;
    private TextView textViewLogout;
    private ImageView imageViewProfile;
    private EditText editTextSearch;
    private Button buttonSaved, buttonApplied;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_SECURE);

        if (!UserSession.isLoggedIn()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        textViewLogout = findViewById(R.id.textViewLogout);
        textViewLogout.setOnClickListener(v -> {
            UserSession.logout();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        imageViewProfile = findViewById(R.id.imageViewProfileHeader);
        if (UserSession.getProfilePicUri() != null) {
            imageViewProfile.setImageURI(UserSession.getProfilePicUri());
        }

        recyclerView = findViewById(R.id.recyclerViewJobs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup Jobs List
        jobList = new ArrayList<>();
        jobList.add(new Job("Senior Java Developer", "Google", "Experience: 5-8 years\n\nResponsibilities:\n- Design scalable backend services.", "Today", "25-12-2026", "Mountain View, CA"));
        jobList.add(new Job("ML Engineer", "Tesla", "Experience: 2-4 years\n\nResponsibilities:\n- Autopilot deep learning.", "Yesterday", "20-12-2026", "Palo Alto, CA"));
        jobList.add(new Job("Mobile App Developer", "Spotify", "Experience: 2-5 years\n\nResponsibilities:\n- Android app features.", "Yesterday", "15-11-2026", "New York, NY"));
        jobList.add(new Job("Full Stack Engineer", "Airbnb", "Experience: 4-6 years\n\nResponsibilities:\n- End-to-end features.", "Yesterday", "30-10-2026", "San Francisco, CA"));
        jobList.add(new Job("Cybersecurity Analyst", "Microsoft", "Experience: 3-6 years\n\nResponsibilities:\n- Threat monitoring.", "2 days ago", "05-12-2026", "Redmond, WA"));
        jobList.add(new Job("Data Scientist", "Amazon", "Experience: 2-5 years\n\nResponsibilities:\n- Predictive modeling.", "2 days ago", "12-11-2026", "Seattle, WA"));
        jobList.add(new Job("Backend Architect", "Netflix", "Experience: 8-12 years\n\nResponsibilities:\n- Distributed systems.", "2 days ago", "28-12-2026", "Los Gatos, CA"));
        jobList.add(new Job("DevOps Engineer", "IBM", "Experience: 2-4 years\n\nResponsibilities:\n- CI/CD automation.", "3 days ago", "01-12-2026", "Austin, TX"));
        jobList.add(new Job("Product Designer", "Apple", "Experience: 3-5 years\n\nResponsibilities:\n- UI design for iOS.", "1 week ago", "01-01-2026", "Cupertino, CA"));
        jobList.add(new Job("Software Engineer", "Meta", "Experience: 3-7 years\n\nResponsibilities:\n- Core infrastructure.", "1 week ago", "10-02-2026", "Menlo Park, CA"));
        jobList.add(new Job("Systems Programmer", "Intel", "Experience: 3-5 years\n\nResponsibilities:\n- Low-level drivers.", "10 days ago", "15-03-2026", "Santa Clara, CA"));

        adapter = new JobAdapter(jobList, job -> {
            Intent intent = new Intent(MainActivity.this, JobDetailActivity.class);
            intent.putExtra("job_data", job);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        // Search
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Bottom Buttons
        buttonSaved = findViewById(R.id.buttonViewSaved);
        buttonApplied = findViewById(R.id.buttonViewApplied);

        buttonSaved.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListDisplayActivity.class);
            intent.putExtra("display_type", "saved");
            startActivity(intent);
        });

        buttonApplied.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListDisplayActivity.class);
            intent.putExtra("display_type", "applied");
            startActivity(intent);
        });
    }
}
