package com.example.pathfinder.Data.PaymentData;

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

}
