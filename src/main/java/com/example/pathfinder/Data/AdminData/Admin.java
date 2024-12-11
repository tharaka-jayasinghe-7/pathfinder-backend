package com.example.pathfinder.Data.AdminData;

import com.example.pathfinder.Data.CourseData.Course;
import com.example.pathfinder.Data.SubscriptionData.Subscription;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    private String firstName;
    private String lastName;
    private String address;
    private Date dob;
    private String mobileNumber;
    private String email;
    private String password;
    private String profilePic;


    //one admin add many subscriptions
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("admin-subscriptions")
    private List<Subscription> subscriptions;

    //one admin add many courses
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("admin-courses")
    private List<Course> courses;
}
