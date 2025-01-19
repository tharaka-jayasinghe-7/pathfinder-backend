package com.example.pathfinder.Service.CompanyService;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CompanyData.CompanyRepo;
import com.example.pathfinder.Data.CompanyData.CompanyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepo companyRepo;


    public Company addCompany(Company company) {
        return companyRepo.save(company);
    }


    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }


    public Optional<Company> getCompanyById(int companyId) {
        return companyRepo.findById(companyId);
    }


    public Company updateCompany(int companyId, Company companyDetails) {
        Optional<Company> existingCompany = companyRepo.findById(companyId);
        if (existingCompany.isPresent()) {
            Company updatedCompany = existingCompany.get();
            updatedCompany.setCompanyName(companyDetails.getCompanyName());
            updatedCompany.setAddress(companyDetails.getAddress());
            updatedCompany.setUrl(companyDetails.getUrl());
            updatedCompany.setIndustry(companyDetails.getIndustry());
            updatedCompany.setEmail(companyDetails.getEmail());
            updatedCompany.setMobile(companyDetails.getMobile());
            updatedCompany.setDate(companyDetails.getDate());
            updatedCompany.setDescription(companyDetails.getDescription());
            updatedCompany.setPassword(companyDetails.getPassword());
            updatedCompany.setImage(companyDetails.getImage());


            return companyRepo.save(updatedCompany);
        }
        return null;
    }


    public String deleteCompanyById(int companyId) {
        Optional<Company> existingCompany = companyRepo.findById(companyId);
        if (existingCompany.isPresent()) {
            companyRepo.delete(existingCompany.get());
            return "Company deleted successfully.";
        }
        return "Company not found.";
    }

    public  Company getCompanyByEmail(String email){
        return companyRepo.findByEmail(email);
    }

    public Optional<Company> getCompanyByName(String companyName){
        return companyRepo.findByCompanyName(companyName);
    }

//    public Company authenticateCompany(String email, String password) {
//        Optional<Company> optionalCompany = companyRepo.findByEmail(email);
//        if (optionalCompany.isPresent()) {
//            Company company = optionalCompany.get();
//            if (company.getPassword().equals(password)) {
//                return company;
//            }
//        }
//        return null;
//    }

    public CompanyResponse loginCompany(String email, String password) {
        // Retrieve company by email
        Company company = companyRepo.findByEmail(email);

        // Check if the company exists and the password matches
        if (company != null && company.getPassword().equals(password)) {
            // Return a response with email and companyId
            return new CompanyResponse(company.getCompanyId(), company.getEmail());
        } else {
            // Throw a 401 Unauthorized error when login fails
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
    }
}



