package com.example.pathfinder.Controller.PaymentController;

import com.example.pathfinder.Data.PaymentData.Payment;
import com.example.pathfinder.Service.PaymentService.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Endpoint to add a payment with companyId in the URL
    @PostMapping("/company/{companyId}/addPayment")
    public ResponseEntity<Payment> addPayment(@PathVariable int companyId, @RequestBody Payment payment) {


        // Call the service to add the payment
        Payment createdPayment = paymentService.addPayment(companyId, payment);

        return ResponseEntity.ok(createdPayment);
    }
}