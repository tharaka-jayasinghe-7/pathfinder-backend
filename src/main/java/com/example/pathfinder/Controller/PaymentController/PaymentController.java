package com.example.pathfinder.Controller.PaymentController;

import com.example.pathfinder.Data.PaymentData.Payment;
import com.example.pathfinder.Service.PaymentService.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/company/{companyId}/addpayment")
    public ResponseEntity<Payment> addPayment(
            @PathVariable int companyId,
            @RequestBody Payment payment) {
        Payment savedPayment = paymentService.addPayment(payment, companyId);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }


    @PutMapping("/company/{companyId}/updatepayment/{paymentId}")
    public ResponseEntity<Payment> updatePayment(
            @PathVariable int paymentId,
            @PathVariable int companyId,
            @RequestBody Payment payment) {
        Payment updatedPayment = paymentService.updatePayment(paymentId, companyId, payment);
        return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
    }


    @GetMapping("/getpayment/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int paymentId) {
        Optional<Payment> payment = paymentService.getPaymentById(paymentId);
        return payment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/getpayments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }


    @DeleteMapping("/deletepayment/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable int paymentId) {
        paymentService.deletePayment(paymentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
