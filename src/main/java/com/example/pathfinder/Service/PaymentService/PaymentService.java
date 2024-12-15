package com.example.pathfinder.Service.PaymentService;

import com.example.pathfinder.Data.PaymentData.Payment;
import com.example.pathfinder.Dto.PaymentDTO.PaymentDTO;
import com.example.pathfinder.Repository.PaymentRepo.PaymentRepo;
import com.example.pathfinder.Repository.CompanyRepo.CompanyRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private ModelMapper modelMapper;

    public PaymentDTO addPayment(PaymentDTO paymentDTO) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setCompany(companyRepo.findById(paymentDTO.getCompanyId()).orElse(null));
        paymentRepo.save(payment);
        return paymentDTO;
    }

    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepo.findAll();
        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .toList();
    }

    public PaymentDTO getPaymentById(int paymentId) {
        Payment payment = paymentRepo.findById(paymentId).orElse(null);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO updatePayment(PaymentDTO paymentDTO) {
        Payment payment = paymentRepo.findById(paymentDTO.getPaymentId()).orElse(null);
        if (payment != null) {
            modelMapper.map(paymentDTO, payment);
            payment.setCompany(companyRepo.findById(paymentDTO.getCompanyId()).orElse(null));
            paymentRepo.save(payment);
        }
        return paymentDTO;
    }

    public String deletePayment(int paymentId) {
        paymentRepo.deleteById(paymentId);
        return "Payment deleted successfully";
    }
}
