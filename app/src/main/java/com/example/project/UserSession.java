package com.example.project;

import android.net.Uri;
import java.util.HashSet;
import java.util.Set;

/**
 * A simple helper class to store global app state like the logged-in user
 * and the list of jobs the user has applied for.
 */
public class UserSession {
    private static boolean isLoggedIn = false;
    private static String userEmail = "";
    private static Uri profilePicUri = null;
    
    // Store unique keys for applied jobs (e.g., "Title @ Company")
    private static Set<String> appliedJobKeys = new HashSet<>();

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void setLoggedIn(boolean loggedIn, String email) {
        isLoggedIn = loggedIn;
        userEmail = email;
    }

    public static void setProfilePicUri(Uri uri) {
        profilePicUri = uri;
    }

    public static Uri getProfilePicUri() {
        return profilePicUri;
    }

    public static void applyForJob(String jobTitle, String company) {
        appliedJobKeys.add(jobTitle + "@" + company);
    }

    public static boolean isJobApplied(String jobTitle, String company) {
        return appliedJobKeys.contains(jobTitle + "@" + company);
    }

    public static void logout() {
        isLoggedIn = false;
        userEmail = "";
        profilePicUri = null;
        appliedJobKeys.clear();
    }
}
