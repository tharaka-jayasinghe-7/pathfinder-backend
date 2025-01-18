package com.example.pathfinder.Data.ApplyData;

public class ApplyDTO {
    private int applyId;
    private int jobId;

    // Constructor
    public ApplyDTO(int applyId, int jobId) {
        this.applyId = applyId;
        this.jobId = jobId;
    }

    // Getters and Setters
    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}

