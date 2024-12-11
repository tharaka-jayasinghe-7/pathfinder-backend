package com.example.pathfinder.Data.CourseData;


import com.example.pathfinder.Data.AdminData.Admin;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;
    private String institute;
    private String webUrl;
    private String industry;
    private String minAgeLimit;
    private String maxAgeLimit;
    private int reqOlPassCount;
    private String duration;
    private String nvqLevel;
    private String type;
    private String coursePic;

    //one course added by one admin
    @ManyToOne
    @JsonBackReference("admin-courses")
    @JoinColumn(name = "adminId")
    private Admin admin;

}
