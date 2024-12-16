package com.example.pathfinder.Service.AdminService;


import com.example.pathfinder.Data.AdminData.Admin;
import com.example.pathfinder.Data.AdminData.AdminRepo;
import com.example.pathfinder.Data.CompanyData.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {



    @Autowired
    private AdminRepo adminRepo;

    public Admin addAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    public Optional<Admin> getAdminByEmail(String email){
        return adminRepo.findByEmail(email);
    }
    public Admin updateAdmin(int adminId, Admin adminDetails) {
        Optional<Admin> existingAdmin = adminRepo.findById(adminId);
        if (existingAdmin.isPresent()) {
            Admin updatedAdmin = existingAdmin.get();
            updatedAdmin.setFirstName(adminDetails.getFirstName());
            updatedAdmin.setLastName(adminDetails.getLastName());
            updatedAdmin.setAddress(adminDetails.getAddress());
            updatedAdmin.setDob(adminDetails.getDob());
            updatedAdmin.setEmail(adminDetails.getEmail());
            updatedAdmin.setMobileNumber(adminDetails.getMobileNumber());
            updatedAdmin.setPassword(adminDetails.getPassword());
            updatedAdmin.setImage(adminDetails.getImage());

            return adminRepo.save(updatedAdmin);
        }
        return null;
    }
}
