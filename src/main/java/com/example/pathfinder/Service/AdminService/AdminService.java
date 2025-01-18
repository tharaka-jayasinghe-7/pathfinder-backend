package com.example.pathfinder.Service.AdminService;

import com.example.pathfinder.Data.AdminData.Admin;
import com.example.pathfinder.Data.AdminData.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    public Admin authenicateAdmin(String email, String password){
        Admin admin = adminRepo.findByEmail(email);

        if (admin != null && admin.getPassword().equals(password)){
            return admin;
        }
        return null;
    }
}
