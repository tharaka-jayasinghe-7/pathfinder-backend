package com.example.pathfinder.Data.SubscriptionData;


import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.JobData.Job;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name ="subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscriptionId;
    private String duration;
    private double price;
    private String features;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("subscription-companies")
    private List<Company> companies;


}
