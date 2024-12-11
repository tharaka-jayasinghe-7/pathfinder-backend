package com.example.pathfinder.Data.PaymentData;

import com.example.pathfinder.Data.CompanyData.Company;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private double amount;
    private String paymentMethod;
    private String cardNumber;
    private Integer code;
    private String billingAddress;
    private String email;
    private String phone;


    //one payment add by one company
    @ManyToOne
    @JsonManagedReference("company-payments")
    @JoinColumn(name = "companyId")
    private Company company;

}
