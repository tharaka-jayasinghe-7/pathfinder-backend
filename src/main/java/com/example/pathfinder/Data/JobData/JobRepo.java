package com.example.pathfinder.Data.JobData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {

    List<Job> findByCompany_CompanyId(int companyId);
}
