package com.example.pathfinder.Data.ApplyData;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.UserData.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applyId;
    private String cv;

    @ManyToOne
    @JsonBackReference("user-apply")
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JsonBackReference("job-apply")
    @JoinColumn(name = "jobId")
    private Job job;

}
