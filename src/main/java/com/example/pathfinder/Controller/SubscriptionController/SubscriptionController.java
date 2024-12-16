package com.example.pathfinder.Controller.SubscriptionController;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.SubscriptionData.Subscription;
import com.example.pathfinder.Service.SubscriptionService.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
@RequestMapping("/subscription")
@CrossOrigin(origins = "http://localhost:3000")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;


}
