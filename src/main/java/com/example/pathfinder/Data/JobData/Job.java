package com.example.pathfinder.Data.JobData;

import com.example.pathfinder.Data.ApplyData.Apply;
import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.InterviewData.Interview;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;
    private String jobImage;
    private String jobTitle;
    private String jobDescription;
    private String location;
    private int reqOlPassCount;
    private int workingHours;
    private String Qualification;

    //one job add by one company
    @ManyToOne
    @JsonManagedReference("company-jobs")
    @JoinColumn(name = "companyId")
    private Company company;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("job-apply")
    private List<Apply> applies;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("job-interviews")
    private List<Interview> interviews;


}