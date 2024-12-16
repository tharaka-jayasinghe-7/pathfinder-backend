package com.example.pathfinder.Data.CompanyData;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
    Optional<Company> findByEmail(String email);

    Optional<Company> findByCompanyName(String companyName);
}
