package com.example.pathfinder.Controller.SubscriptionController;

import com.example.pathfinder.Data.SubscriptionData.Subscription;
import com.example.pathfinder.Service.SubscriptionService.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/addSubscription")
    public ResponseEntity<Subscription> addSubscription(@RequestBody Subscription subscription) {
        Subscription savedSubscription = subscriptionService.addSubscription(subscription);
        return new ResponseEntity<>(savedSubscription, HttpStatus.CREATED);
    }

    @GetMapping("/get/{subscriptionId}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable int subscriptionId) {
        Optional<Subscription> subscription = subscriptionService.getSubscriptionById(subscriptionId);
        return subscription.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscription();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSubscription/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable int subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
