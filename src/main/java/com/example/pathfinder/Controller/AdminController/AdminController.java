package com.example.pathfinder.Controller.AdminController;

import com.example.pathfinder.Data.AdminData.Admin;
import com.example.pathfinder.Data.AdminData.AdminResponse;
import com.example.pathfinder.Service.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/adminLogin")
    public ResponseEntity<Object> adminLogin(@RequestBody Admin loginAdmin){
        try{
            Admin admin = adminService.authenicateAdmin(loginAdmin.getEmail(), loginAdmin.getPassword());

            if(admin == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
            }

            AdminResponse response = new AdminResponse(admin.getAdminId(), admin.getEmail());
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }
}
