package com.example.pathfinder.Data.CompanyData;

import com.example.pathfinder.Data.InterviewData.Interview;
import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.PaymentData.Payment;
import com.example.pathfinder.Data.PostData.Post;
import com.example.pathfinder.Data.SubscriptionData.Subscription;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    private String companyName;

    @Lob
    private Blob image;

    private String address;
    private String url;
    private String industry;
    private String email;
    private String mobile;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String description;
    private String password;

    // One company has many interviews
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("company-interviews")
    private List<Interview> interviews;

    // One company can have many jobs
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("company-jobs")
    private List<Job> jobs;

    // One company can have many posts
    // Bi-directional relationship with Payment
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("company-posts")  // Add this annotation to avoid recursion
    private List<Post> posts;  // Replace 'Post' with your actual entity type

    // One company can have many payments
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("company-payments")  // Managed reference for company
    private List<Payment> payments;


    // One company has one subscription
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("subscription-companies")
    @JoinColumn(name = "subscriptionId", nullable = true)
    private Subscription subscription;

    // Getters and Setters
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
