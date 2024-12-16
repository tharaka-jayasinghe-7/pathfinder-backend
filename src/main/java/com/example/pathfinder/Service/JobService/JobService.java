package com.example.pathfinder.Service.JobService;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CompanyData.CompanyRepo;
import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.JobData.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    JobRepo jobRepo;

    @Autowired
    CompanyRepo companyRepo;

    public Job addJob(Job job, int companyId) {
        Company company = companyRepo.findById(companyId).get();
        job.setCompany(company);
        return jobRepo.save(job);
    }
}
