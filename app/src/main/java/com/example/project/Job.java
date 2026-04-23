package com.example.project;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Job implements Serializable {
    private String title;
    private String company;
    private String description;
    private String datePosted;
    private String closingDate; // Format: dd-MM-yyyy
    private String location;

    public Job(String title, String company, String description, String datePosted, String closingDate, String location) {
        this.title = title;
        this.company = company;
        this.description = description;
        this.datePosted = datePosted;
        this.closingDate = closingDate;
        this.location = location;
    }

    public String getTitle() { return title; }
    public String getCompany() { return company; }
    public String getDescription() { return description; }
    public String getDatePosted() { return datePosted; }
    public String getClosingDate() { return closingDate; }
    public String getLocation() { return location; }

    public boolean isClosed() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            Date expiry = sdf.parse(closingDate);
            Date now = new Date();
            // Compare only dates (ignoring time for simplicity)
            return expiry != null && expiry.before(now);
        } catch (Exception e) {
            return false;
        }
    }
}
