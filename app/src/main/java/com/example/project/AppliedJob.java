package com.example.project;

import android.net.Uri;
import java.io.Serializable;

public class AppliedJob implements Serializable {
    private Job job;
    private String resumeUriString;

    public AppliedJob(Job job, Uri resumeUri) {
        this.job = job;
        this.resumeUriString = resumeUri != null ? resumeUri.toString() : null;
    }

    public Job getJob() { return job; }
    public Uri getResumeUri() { 
        return resumeUriString != null ? Uri.parse(resumeUriString) : null; 
    }
}
