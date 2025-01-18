package com.example.pathfinder.Data.JobData;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class JobDTO {
    private int jobId;
    private String jobTitle;
    private String jobDescription;
    private String location;
    private int reqOlPassCount;
    private int workingHours;
    private String qualification;
    private int companyId;  // Add companyId here
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jobDate;

    public int getJobId() {
        return jobId;
    }

    public Date getJobDate() {
        return jobDate;
    }

    public void setJobDate(Date jobDate) {
        this.jobDate = jobDate;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getReqOlPassCount() {
        return reqOlPassCount;
    }

    public void setReqOlPassCount(int reqOlPassCount) {
        this.reqOlPassCount = reqOlPassCount;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}

