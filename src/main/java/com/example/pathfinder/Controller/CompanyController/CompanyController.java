package com.example.pathfinder.Controller.CompanyController;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CompanyData.CompanyResponse;
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

    @PostMapping("/addCompany")
    public ResponseEntity<Company> addCompany(@ModelAttribute Company company, @RequestParam("profilePic") MultipartFile file)
            throws IOException, SQLException {
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        company.setImage(blob);

        Company savedCompany = companyService.addCompany(company);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }



    @GetMapping("/getCompany/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") int companyId) {
        Optional<Company> company = companyService.getCompanyById(companyId);

        if (company.isPresent()) {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Company> getCompanyByEmail(@PathVariable String email) {
        Optional<Company> company = companyService.getCompanyByEmail(email);

        if (company.isPresent()) {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCompanyByName/{companyName}")
    public ResponseEntity<Company> getCompanyByName(@PathVariable String companyName) {
        Optional<Company> company = companyService.getCompanyByName(companyName);

        if (company.isPresent()) {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateCompany/{companyId}")
    public ResponseEntity<Company> updateCompany(
            @PathVariable int companyId,
            @ModelAttribute Company company,
            @RequestParam("profilePic") MultipartFile file) throws IOException, SQLException {

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

    @DeleteMapping("/deleteCompany/{companyId}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable int companyId) {
        String message = companyService.deleteCompanyById(companyId);

        if (message.equals("Company deleted successfully.")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/companyLogin")
    public ResponseEntity<?> companyLogin(@RequestBody Company loginCompany) {
        try {
            Company company = companyService.authenticateCompany(loginCompany.getEmail(), loginCompany.getPassword());

            if (company == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Email or Password");
            }

            // Create a CompanyResponse with companyId and email
            CompanyResponse response = new CompanyResponse(company.getCompanyId(), company.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @GetMapping("/{companyId}/image")
    public ResponseEntity<byte[]> getImageByCompanyId(@PathVariable int companyId){
        Optional<Company> company = companyService.getCompanyById(companyId);

        if(company.isPresent()){
            try{
                Blob blob = company.get().getImage();
                byte[] image = blob.getBytes(1,(int) blob.length());
                return new ResponseEntity<>(image, HttpStatus.OK);
            }catch (SQLException e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}


