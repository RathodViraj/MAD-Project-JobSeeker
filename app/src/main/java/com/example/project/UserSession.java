package com.example.project;

import android.net.Uri;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserSession {
    private static boolean isLoggedIn = false;
    private static String userEmail = "";
    private static Uri profilePicUri = null;
    
    private static Set<String> appliedJobKeys = new HashSet<>();
    private static List<AppliedJob> appliedJobsList = new ArrayList<>();
    private static List<Job> savedJobsList = new ArrayList<>();

    public static boolean isLoggedIn() { return isLoggedIn; }
    public static void setLoggedIn(boolean loggedIn, String email) {
        isLoggedIn = loggedIn;
        userEmail = email;
    }

    public static void setProfilePicUri(Uri uri) { profilePicUri = uri; }
    public static Uri getProfilePicUri() { return profilePicUri; }

    public static void applyForJob(Job job, Uri resumeUri) {
        appliedJobKeys.add(job.getTitle() + "@" + job.getCompany());
        appliedJobsList.add(new AppliedJob(job, resumeUri));
    }

    public static boolean isJobApplied(String jobTitle, String company) {
        return appliedJobKeys.contains(jobTitle + "@" + company);
    }

    public static List<AppliedJob> getAppliedJobs() { return appliedJobsList; }

    public static void saveJob(Job job) {
        if (!isJobSaved(job)) {
            savedJobsList.add(job);
        }
    }

    public static void unsaveJob(Job job) {
        savedJobsList.removeIf(j -> j.getTitle().equals(job.getTitle()) && j.getCompany().equals(job.getCompany()));
    }

    public static boolean isJobSaved(Job job) {
        for (Job j : savedJobsList) {
            if (j.getTitle().equals(job.getTitle()) && j.getCompany().equals(job.getCompany())) return true;
        }
        return false;
    }

    public static List<Job> getSavedJobs() { return savedJobsList; }

    public static void logout() {
        isLoggedIn = false;
        userEmail = "";
        profilePicUri = null;
        appliedJobKeys.clear();
        appliedJobsList.clear();
        savedJobsList.clear();
    }
}
