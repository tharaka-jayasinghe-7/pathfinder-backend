package com.example.pathfinder.Service.JobService;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CompanyData.CompanyRepo;
import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.JobData.JobRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    JobRepo jobRepo;

    @Autowired
    CompanyRepo companyRepo;

    public Job addJob(Job job, int companyId) {
        Company company = companyRepo.findById(companyId).orElseThrow(() -> new RuntimeException("Company not found"));
        job.setCompany(company);
        return jobRepo.save(job);
    }

    @Transactional
    public Optional<Job> getJobById(int id) {
        return jobRepo.findById(id);
    }

    @Transactional
    public List<Job> getJobs() {
        return jobRepo.findAll();
    }

    public void deleteJob(int jobId) {
        jobRepo.deleteById(jobId);
    }

    public List<Job> getJobsByCompanyId(int companyId) {
        // Fetch jobs for the given companyId from the repository
        return jobRepo.findByCompanyCompanyId(companyId);
    }

}
