package com.example.project;

import java.io.Serializable;

// Model class to store job details
public class Job implements Serializable {
    private String title;
    private String company;
    private String description;
    private String datePosted;

    public Job(String title, String company, String description, String datePosted) {
        this.title = title;
        this.company = company;
        this.description = description;
        this.datePosted = datePosted;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public String getDatePosted() {
        return datePosted;
    }
}
