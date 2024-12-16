package com.example.pathfinder.Data.SubscriptionData;


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


}
