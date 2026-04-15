package com.example.project;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // CHECK LOGIN STATUS FIRST
        if (!UserSession.isLoggedIn()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        // Initialize Logout button
        textViewLogout = findViewById(R.id.textViewLogout);
        textViewLogout.setOnClickListener(v -> {
            UserSession.logout();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Load profile photo in header if available
        imageViewProfile = findViewById(R.id.imageViewProfileHeader);
        if (UserSession.getProfilePicUri() != null) {
            imageViewProfile.setImageURI(UserSession.getProfilePicUri());
        }

        recyclerView = findViewById(R.id.recyclerViewJobs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        jobList = new ArrayList<>();
        
        jobList.add(new Job("Senior Java Developer", "Google", 
            "Experience: 5-8 years\n\n" +
            "Responsibilities:\n" +
            "- Design and implement scalable backend services using Java and Spring Boot.\n" +
            "- Optimize application performance and ensure high availability.\n" +
            "- Collaborate with cross-functional teams to define system architecture.\n" +
            "- Mentor junior developers and conduct code reviews.\n" +
            "- Troubleshoot and resolve complex technical issues in production.\n\n" +
            "Perks:\n" +
            "- Competitive equity package.\n" +
            "- Free gourmet meals and snacks.\n" +
            "- Comprehensive health and wellness programs.", 
            "Today"));

        jobList.add(new Job("Product Designer", "Apple", 
            "Experience: 3-5 years\n\n" +
            "Responsibilities:\n" +
            "- Create intuitive and elegant user interfaces for iOS and macOS.\n" +
            "- Develop high-fidelity prototypes to test user interactions.\n" +
            "- Conduct user research and translate insights into design requirements.\n" +
            "- Work closely with engineers to ensure design integrity during implementation.\n\n" +
            "Perks:\n" +
            "- Significant product discounts.\n" +
            "- On-site fitness centers.\n" +
            "- Global travel opportunities for research.", 
            "Today"));

        jobList.add(new Job("ML Engineer", "Tesla", 
            "Experience: 2-4 years\n\n" +
            "Responsibilities:\n" +
            "- Develop and train deep learning models for autonomous driving.\n" +
            "- Implement efficient computer vision algorithms on embedded hardware.\n" +
            "- Analyze large datasets to improve model accuracy and safety.\n" +
            "- Collaborate with hardware teams to optimize neural network inference.\n" +
            "- Stay updated with the latest research in AI and robotics.\n\n" +
            "Perks:\n" +
            "- Access to the latest Tesla vehicle technology.\n" +
            "- Stock purchase plan.\n" +
            "- High-impact work environment.", 
            "Yesterday"));

        jobList.add(new Job("Mobile App Developer", "Spotify", 
            "Experience: 2-5 years\n\n" +
            "Responsibilities:\n" +
            "- Build new features for the Spotify Android application.\n" +
            "- Improve app performance and audio streaming reliability.\n" +
            "- Write clean, maintainable code using Kotlin and Java.\n" +
            "- Participate in design and code reviews.\n" +
            "- Ensure a seamless user experience across different devices.\n\n" +
            "Perks:\n" +
            "- Premium Spotify subscription for life.\n" +
            "- Flexible working hours.\n" +
            "- Personal development budget.", 
            "Yesterday"));

        jobList.add(new Job("Full Stack Engineer", "Airbnb", 
            "Experience: 4-6 years\n\n" +
            "Responsibilities:\n" +
            "- Develop end-to-end features using React and Ruby on Rails.\n" +
            "- Build responsive and accessible web interfaces.\n" +
            "- Design and maintain efficient database schemas.\n" +
            "- Implement robust security measures for user data.\n" +
            "- Participate in on-call rotations to ensure site reliability.\n\n" +
            "Perks:\n" +
            "- Annual travel credits for Airbnb stays.\n" +
            "- Paid volunteer time.\n" +
            "- Home office stipend.", 
            "Yesterday"));

        jobList.add(new Job("Cybersecurity Analyst", "Microsoft", 
            "Experience: 3-6 years\n\n" +
            "Responsibilities:\n" +
            "- Monitor and respond to security threats across cloud infrastructure.\n" +
            "- Perform vulnerability assessments and penetration testing.\n" +
            "- Develop and implement security policies and standards.\n" +
            "- Conduct forensic analysis of security incidents.\n" +
            "- Educate employees on security best practices.\n\n" +
            "Perks:\n" +
            "- Relocation assistance.\n" +
            "- Tuition reimbursement.\n" +
            "- Diversity and inclusion programs.", 
            "2 days ago"));

        jobList.add(new Job("Data Scientist", "Amazon", 
            "Experience: 2-5 years\n\n" +
            "Responsibilities:\n" +
            "- Build predictive models to optimize supply chain logistics.\n" +
            "- Perform A/B testing to evaluate new feature impact.\n" +
            "- Visualize complex data insights for business stakeholders.\n" +
            "- Develop automated data pipelines using Python and SQL.\n" +
            "- Research and apply new machine learning techniques.\n\n" +
            "Perks:\n" +
            "- Career choice program for vocational training.\n" +
            "- Employee discount on Amazon.com.\n" +
            "- Casual dress code.", 
            "2 days ago"));

        jobList.add(new Job("Backend Architect", "Netflix", 
            "Experience: 8-12 years\n\n" +
            "Responsibilities:\n" +
            "- Define the long-term architectural vision for streaming services.\n" +
            "- Lead the design of highly distributed, fault-tolerant systems.\n" +
            "- Evaluate and adopt new technologies to improve scalability.\n" +
            "- Collaborate with senior leadership on technical strategy.\n" +
            "- Mentor principal engineers across the organization.\n\n" +
            "Perks:\n" +
            "- Market-leading compensation.\n" +
            "- No formal vacation policy (take what you need).\n" +
            "- Fully paid parental leave.", 
            "2 days ago"));

        jobList.add(new Job("Software Engineer", "Meta", 
            "Experience: 3-7 years\n\n" +
            "Responsibilities:\n" +
            "- Develop core infrastructure for social networking platforms.\n" +
            "- Optimize data storage and retrieval for billions of users.\n" +
            "- Implement privacy-focused features and protocols.\n" +
            "- Build tools to improve developer productivity.\n" +
            "- Contribute to open-source projects managed by Meta.\n\n" +
            "Perks:\n" +
            "- On-site health clinics.\n" +
            "- Wellness allowance.\n" +
            "- Sabbatical program every 5 years.", 
            "2 days ago"));

        jobList.add(new Job("DevOps Engineer", "IBM", 
            "Experience: 2-4 years\n\n" +
            "Responsibilities:\n" +
            "- Automate software deployment processes using Jenkins and Ansible.\n" +
            "- Manage containerized workloads on Kubernetes clusters.\n" +
            "- Implement monitoring and alerting for hybrid cloud environments.\n" +
            "- Troubleshoot infrastructure issues and performance bottlenecks.\n" +
            "- Enhance system security and compliance through automation.\n\n" +
            "Perks:\n" +
            "- Extensive online learning resources (IBM SkillsBuild).\n" +
            "- Group life insurance.\n" +
            "- Retirement savings plan.", 
            "3 days ago"));

        jobList.add(new Job("AI Researcher", "NVIDIA", 
            "Experience: 4-8 years\n\n" +
            "Responsibilities:\n" +
            "- Conduct original research in generative AI and graphics.\n" +
            "- Publish findings in top-tier conferences like CVPR or NeurIPS.\n" +
            "- Prototype new algorithms using CUDA and PyTorch.\n" +
            "- Collaborate with product teams to integrate AI into GPUs.\n" +
            "- Manage research collaborations with academic institutions.\n\n" +
            "Perks:\n" +
            "- Access to world-class supercomputing clusters.\n" +
            "- Patent filing bonuses.\n" +
            "- Flexible work-from-home options.", 
            "3 days ago"));

        jobList.add(new Job("Systems Programmer", "Intel", 
            "Experience: 3-5 years\n\n" +
            "Responsibilities:\n" +
            "- Develop low-level drivers and firmware for new hardware.\n" +
            "- Optimize software performance for specific CPU architectures.\n" +
            "- Debug complex hardware-software interaction issues.\n" +
            "- Write robust code in C and assembly language.\n" +
            "- Participate in architectural reviews for next-gen silicon.\n\n" +
            "Perks:\n" +
            "- Performance-based bonuses.\n" +
            "- Sabbatical leave program.\n" +
            "- On-site cafes and shops.", 
            "3 days ago"));

        jobList.add(new Job("Database Engineer", "Oracle", 
            "Experience: 5-7 years\n\n" +
            "Responsibilities:\n" +
            "- Develop and tune relational database engine components.\n" +
            "- Implement high-availability and disaster recovery features.\n" +
            "- Optimize SQL execution and query processing.\n" +
            "- Research and implement new indexing techniques.\n" +
            "- Support enterprise customers with critical database issues.\n\n" +
            "Perks:\n" +
            "- Global mobility program.\n" +
            "- Employee stock purchase plan.\n" +
            "- Comprehensive dental and vision insurance.", 
            "3 days ago"));

        jobList.add(new Job("Cloud Security Engineer", "Adobe", 
            "Experience: 4-6 years\n\n" +
            "Responsibilities:\n" +
            "- Secure Adobe Creative Cloud infrastructure on AWS and Azure.\n" +
            "- Implement identity and access management (IAM) controls.\n" +
            "- Automate security compliance checks using serverless functions.\n" +
            "- Respond to security alerts and investigate potential breaches.\n" +
            "- Conduct threat modeling for new cloud-based features.\n\n" +
            "Perks:\n" +
            "- Annual wellness reimbursement.\n" +
            "- Adobe Creative Cloud subscription for free.\n" +
            "- Commuter benefits.", 
            "3 days ago"));

        jobList.add(new Job("Infrastructure Engineer", "Uber", 
            "Experience: 3-5 years\n\n" +
            "Responsibilities:\n" +
            "- Build and scale real-time dispatching and routing systems.\n" +
            "- Manage large-scale distributed databases like Cassandra.\n" +
            "- Optimize network latency for global transportation services.\n" +
            "- Develop internal tools for infrastructure automation.\n" +
            "- Participate in capacity planning and performance testing.\n\n" +
            "Perks:\n" +
            "- Monthly Uber credits.\n" +
            "- Free lunch and dinner daily.\n" +
            "- Gym membership reimbursement.", 
            "3 days ago"));

        adapter = new JobAdapter(jobList, new JobAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Job job) {
                Intent intent = new Intent(MainActivity.this, JobDetailActivity.class);
                intent.putExtra("job_data", job);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
