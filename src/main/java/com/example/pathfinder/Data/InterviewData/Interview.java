package com.example.pathfinder.Data.InterviewData;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.UserData.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int interviewId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date interviewDate;
    private String description;

    //one interview has one company
    @ManyToOne
    @JsonBackReference("company-interviews")
    @JoinColumn(name = "companyId")
    private Company company;

    @ManyToOne
    @JsonBackReference("job-interviews")
    @JoinColumn(name = "jobId")
    private Job job;

    @ManyToMany(mappedBy = "interviews", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private List<User> users;

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}