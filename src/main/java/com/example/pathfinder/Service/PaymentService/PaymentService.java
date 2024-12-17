package com.example.pathfinder.Service.PaymentService;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CompanyData.CompanyRepo;
import com.example.pathfinder.Data.PaymentData.Payment;
import com.example.pathfinder.Data.PaymentData.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private CompanyRepo companyRepo;

    public Payment addPayment(Payment payment, int companyId) {
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + companyId));
        payment.setCompany(company);
        return paymentRepo.save(payment);
    }


    public Payment updatePayment(int paymentId, int companyId, Payment updatedPayment) {
        Payment existingPayment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));

        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + companyId));

        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
        existingPayment.setCardNumber(updatedPayment.getCardNumber());
        existingPayment.setCode(updatedPayment.getCode());
        existingPayment.setBillingAddress(updatedPayment.getBillingAddress());
        existingPayment.setEmail(updatedPayment.getEmail());
        existingPayment.setPhone(updatedPayment.getPhone());
        existingPayment.setCompany(company);

        return paymentRepo.save(existingPayment);
    }


    public Optional<Payment> getPaymentById(int paymentId) {
        return paymentRepo.findById(paymentId);
    }


    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }


    public void deletePayment(int paymentId) {
        paymentRepo.deleteById(paymentId);
    }
}
