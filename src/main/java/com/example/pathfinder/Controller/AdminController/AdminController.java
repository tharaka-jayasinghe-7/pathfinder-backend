package com.example.pathfinder.Controller.AdminController;

import com.example.pathfinder.Data.AdminData.Admin;
import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Service.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Build Admin Create REST API
    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> addAdmin(@ModelAttribute Admin admin, @RequestParam("profilePic") MultipartFile file)
            throws IOException, SQLException {

        byte[] bytes = file.getBytes();

        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        admin.setImage(blob);


        Admin savedAdmin = adminService.addAdmin(admin);


        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }
    //Build Admin GetById REST API
    @GetMapping("{email}")
    public ResponseEntity<Admin> getCompanyByEmail (@PathVariable String email){
        Optional<Admin> admin = adminService.getAdminByEmail(email);

        if(admin.isPresent()){
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Build Admin Update REST API
    @PutMapping("/updateadmin/{adminId}")
    public ResponseEntity<Admin> updateAdmin(
            @PathVariable int adminId,
            @ModelAttribute Admin admin,
            @RequestParam("profilePic") MultipartFile file) throws IOException, SQLException {

        // Process the file (same as in addAdmin)
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        admin.setImage(blob);

        Admin updatedAdmin= adminService.updateAdmin(adminId, admin);

        if (updatedAdmin != null) {
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
