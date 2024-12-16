package com.example.pathfinder.Data.AdminData;

import com.example.pathfinder.Data.CompanyData.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);

}
