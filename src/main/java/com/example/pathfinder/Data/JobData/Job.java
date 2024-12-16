package com.example.pathfinder.Data.JobData;

import com.example.pathfinder.Data.ApplyData.Apply;
import com.example.pathfinder.Data.BlobSerializer.BlobSerializer;
import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.InterviewData.Interview;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.sql.Blob;
import java.util.List;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;
    private String jobTitle;
    private String jobDescription;
    private String location;
    private int reqOlPassCount;
    private int workingHours;
    private String Qualification;

    @Lob
    @JsonSerialize(using = BlobSerializer.class)
    private Blob jobImage;

    //one job add by one company
    @ManyToOne
    @JsonBackReference("company-jobs")
    @JoinColumn(name = "companyId")
    private Company company;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("job-apply")
    private List<Apply> applies;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("job-interviews")
    private List<Interview> interviews;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public Blob getJobImage() {
        return jobImage;
    }

    public void setJobImage(Blob jobImage) {
        this.jobImage = jobImage;
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
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Apply> getApplies() {
        return applies;
    }

    public void setApplies(List<Apply> applies) {
        this.applies = applies;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }
}
