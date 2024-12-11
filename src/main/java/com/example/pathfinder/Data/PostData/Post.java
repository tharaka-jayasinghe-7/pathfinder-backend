package com.example.pathfinder.Data.PostData;

import com.example.pathfinder.Data.CompanyData.Company;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String title;
    private String content;
    private String image;
    private String type;
    private Date date;


    //one post add by one company
    @ManyToOne
    @JsonManagedReference("company-posts")
    @JoinColumn(name = "companyId")
    private Company company;
}
