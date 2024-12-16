package com.example.pathfinder.Service.SubscriptionService;

import com.example.pathfinder.Data.CourseData.Course;
import com.example.pathfinder.Data.SubscriptionData.Subscription;
import com.example.pathfinder.Data.SubscriptionData.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;
    public Subscription addSubscription(Subscription subscription) {
        return subscriptionRepo.save(subscription);
    }
}
