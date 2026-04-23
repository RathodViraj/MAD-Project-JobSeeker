package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ListDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textViewTitle, textViewBack;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        type = getIntent().getStringExtra("display_type");
        recyclerView = findViewById(R.id.recyclerViewDisplay);
        textViewTitle = findViewById(R.id.textViewListTitle);
        textViewBack = findViewById(R.id.textViewBack);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        textViewBack.setOnClickListener(v -> finish());

        if ("applied".equals(type)) {
            textViewTitle.setText("Applied Jobs");
            List<AppliedJob> appliedJobs = UserSession.getAppliedJobs();
            AppliedJobAdapter adapter = new AppliedJobAdapter(appliedJobs);
            recyclerView.setAdapter(adapter);
        } else if ("saved".equals(type)) {
            textViewTitle.setText("Saved Jobs");
            List<Job> savedJobs = UserSession.getSavedJobs();
            JobAdapter adapter = new JobAdapter(savedJobs, job -> {
                Intent intent = new Intent(ListDisplayActivity.this, JobDetailActivity.class);
                intent.putExtra("job_data", job);
                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);
        }
    }
}
