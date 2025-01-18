package com.example.pathfinder.Data.InterviewData;

public class InterviewNotificationDTO {

    private int interviewId;
    private String interviewDate;
    private String description;
    private String companyName;
    private String jobTitle;

    // Constructor
    public InterviewNotificationDTO(int interviewId, String interviewDate, String description, String companyName, String jobTitle) {
        this.interviewId = interviewId;
        this.interviewDate = interviewDate;
        this.description = description;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
    }

    // Getters and Setters
    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
