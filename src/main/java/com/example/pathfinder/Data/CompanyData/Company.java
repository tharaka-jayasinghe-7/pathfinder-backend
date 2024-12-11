package com.example.pathfinder.Data.CompanyData;

import com.example.pathfinder.Data.InterviewData.Interview;
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

}
