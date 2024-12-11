package com.example.pathfinder.Data.CompanyData;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
}
