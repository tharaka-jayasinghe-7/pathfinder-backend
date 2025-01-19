package com.example.pathfinder.Controller.JobController;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CompanyData.CompanyRepo;
import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.JobData.JobDTO;
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
    public ResponseEntity<JobDTO> getJobById(@PathVariable int jobId) {
        Optional<Job> job = jobService.getJobById(jobId);

        if (job.isPresent()) {
            Job jobDetails = job.get();
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJobId(jobDetails.getJobId());
            jobDTO.setJobTitle(jobDetails.getJobTitle());
            jobDTO.setJobDescription(jobDetails.getJobDescription());
            jobDTO.setLocation(jobDetails.getLocation());
            jobDTO.setReqOlPassCount(jobDetails.getReqOlPassCount());
            jobDTO.setWorkingHours(jobDetails.getWorkingHours());
            jobDTO.setQualification(jobDetails.getQualification());
            jobDTO.setJobDate(jobDetails.getJobDate());
            jobDTO.setCompanyId(jobDetails.getCompany().getCompanyId());  // Set the companyId here

            return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

    @GetMapping("/{jobId}/image")
    public ResponseEntity<byte[]> getImageByJobId(@PathVariable int jobId) {
        Optional<Job> job = jobService.getJobById(jobId);

        if (job.isPresent()) {
            try {
                Blob blob = job.get().getJobImage(); // Assuming this returns a Blob
                byte[] image = blob.getBytes(1, (int) blob.length()); // Convert Blob to byte[]
                return new ResponseEntity<>(image, HttpStatus.OK);
            } catch (SQLException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Handle SQL exceptions
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/company/{companyId}/jobs")
    public ResponseEntity<List<Job>> getJobsByCompanyId(@PathVariable int companyId) {
        // Fetch jobs by companyId
        List<Job> jobs = jobService.getJobsByCompanyId(companyId);

        // Check if jobs are found
        if (jobs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no jobs found
        }

        return new ResponseEntity<>(jobs, HttpStatus.OK); // Return 200 with jobs list
    }


}
