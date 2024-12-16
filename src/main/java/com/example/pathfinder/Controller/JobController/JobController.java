package com.example.pathfinder.Controller.JobController;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CompanyData.CompanyRepo;
import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.UserData.User;
import com.example.pathfinder.Service.CompanyService.CompanyService;
import com.example.pathfinder.Service.JobService.JobService;
import com.example.pathfinder.Service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/company/{companyId}/addJob")
    public ResponseEntity<Job> addJob(
            @RequestParam("jobTitle") String jobTitle,
            @RequestParam("jobDescription") String jobDescription,
            @RequestParam("location") String location,
            @RequestParam("reqOlPassCount") int reqOlPassCount,
            @RequestParam("workingHours") int workingHours,
            @RequestParam("Qualification") String Qualification,
            @RequestParam("jobImage") MultipartFile file,
            @PathVariable int companyId) throws IOException, SQLException {

        // Convert file to byte array
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        // Create job object and set fields
        Job job = new Job();
        job.setJobTitle(jobTitle);
        job.setJobDescription(jobDescription);
        job.setLocation(location);
        job.setReqOlPassCount(reqOlPassCount);
        job.setWorkingHours(workingHours);
        job.setQualification(Qualification);
        job.setJobImage(blob);

        // Delegate the setting of the company and saving the job to the service layer
        Job savedJob = jobService.addJob(job, companyId);

        // Return the saved job object with a 201 status code (Created)
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    @GetMapping("/getJob/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable int jobId) {
        Optional<Job> job = jobService.getJobById(jobId);

        if(job.isPresent()) {
            return new ResponseEntity<>(job.get(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getJobs")
    public List<Job> getAllJobs() {
        List<Job> jobs = jobService.getJobs();
        return jobs;
    }

    @DeleteMapping("/deleteJob/{jobId}")
    public void deleteJob(@PathVariable int jobId) {
        jobService.deleteJob(jobId);
    }
}
