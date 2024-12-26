package com.example.pathfinder.Service.SubscriptionService;

import com.example.pathfinder.Data.AdminData.AdminRepo;
import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CompanyData.CompanyRepo;
import com.example.pathfinder.Data.SubscriptionData.Subscription;
import com.example.pathfinder.Data.SubscriptionData.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Autowired
    private CompanyRepo companyRepo;

    public Subscription addSubscription(Subscription subscription){
        return subscriptionRepo.save(subscription);
    }

    public Optional<Subscription> getSubscriptionById(int subscriptionId){
        return subscriptionRepo.findById(subscriptionId);
    }

    public List<Subscription> getAllSubscription(){
        return subscriptionRepo.findAll();
    }

    public void deleteSubscription(int subscriptionId){
        subscriptionRepo.deleteById(subscriptionId);
    }
}
