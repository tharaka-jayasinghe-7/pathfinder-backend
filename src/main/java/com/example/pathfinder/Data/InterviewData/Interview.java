package com.example.pathfinder.Data.InterviewData;

import com.example.pathfinder.Data.CompanyData.Company;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int interviewId;
    private Date interviewDate;
    private String description;

    //one interview has one company
    @ManyToOne
    @JsonBackReference("company-interviews")
    @JoinColumn(name = "companyId")
    private Company company;


}
