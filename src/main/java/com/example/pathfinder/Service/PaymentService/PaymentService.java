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

    // Method to add payment with companyId
    public Payment addPayment(int companyId, Payment payment){
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));
        payment.setCompany(company);

        return paymentRepo.save(payment);
    }

}