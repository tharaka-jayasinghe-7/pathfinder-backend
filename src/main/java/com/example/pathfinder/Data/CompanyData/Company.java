package com.example.pathfinder.Data.CompanyData;

import com.example.pathfinder.Data.InterviewData.Interview;
import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.PaymentData.Payment;
import com.example.pathfinder.Data.PostData.Post;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name ="company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;
    private String companyName;
    private String profilePic;
    private String address;
    private String url;
    private String industry;
    private String email;
    private String mobile;
    private Date date;
    private String description;
    private String password;

    //one company has many interviews
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("company-interviews")
    private List<Interview> interviews;

    //one company add many jobs
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("company-jobs")
    private List<Job> jobs;

    //one company add many post
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("company-posts")
    private List<Post> posts;

    //one company do many payment
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("company-payments")
    private List<Payment> payments;

}
