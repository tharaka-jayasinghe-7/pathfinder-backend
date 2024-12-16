package com.example.pathfinder.Controller.JobController;

import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Service.JobService.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/company/{companyId}/addJob")
    public ResponseEntity<Job> addJob (@PathVariable int companyId, @RequestBody Job job) {
        Job createdJob = jobService.addJob(job, companyId);
        return ResponseEntity.ok(createdJob);
    }
}
