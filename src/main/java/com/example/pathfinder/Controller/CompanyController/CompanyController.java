package com.example.pathfinder.Controller.CompanyController;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Service.CompanyService.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Build Company Create REST API
    @PostMapping("/addcompany")
    public ResponseEntity<Company> addCompany(@ModelAttribute Company company, @RequestParam("profilePic") MultipartFile file)
            throws IOException, SQLException {

        byte[] bytes = file.getBytes();

        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        company.setImage(blob);


        Company savedCompany = companyService.addCompany(company);


        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    // Build Company GetAll REST API
    @GetMapping("/getcompanies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    // Build Company GetById REST API
    @GetMapping("/getcompany/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int companyId) {
        Optional<Company> company = companyService.getCompanyById(companyId);


        if (company.isPresent()) {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Build Company Update REST API
    @PutMapping("/updatecompany/{companyId}")
    public ResponseEntity<Company> updateCompany(
            @PathVariable int companyId,
            @ModelAttribute Company company,
            @RequestParam("profilePic") MultipartFile file) throws IOException, SQLException {

        // Process the file (same as in addCompany)
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        company.setImage(blob);

        Company updatedCompany = companyService.updateCompany(companyId, company);

        if (updatedCompany != null) {
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Build Company Delete REST API
    @DeleteMapping("/deletecompany/{companyId}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable int companyId) {
        String message = companyService.deleteCompanyById(companyId);

        if (message.equals("Company deleted successfully.")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    //Build Company GetById REST API
    @GetMapping("{email}")
    public ResponseEntity<Company> getCompanyById (@PathVariable String email){
        Optional<Company> company = companyService.getCompanyByEmail(email);

        if(company.isPresent()){
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Build Company GetByName REST API
    @GetMapping("/getcompanybyname/{companyName}")
    public ResponseEntity<Company> getCompanyByName(@PathVariable String companyName) {
        Optional<Company> company = companyService.getCompanyByName(companyName);

        if (company.isPresent()) {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
