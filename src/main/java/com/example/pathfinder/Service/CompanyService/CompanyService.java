package com.example.pathfinder.Service.CompanyService;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CompanyData.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    // Method to add a company
    public Company addCompany(Company company) {
        return companyRepo.save(company);
    }

    // Method to get all companies
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    // Method to get a company by its ID
    public Optional<Company> getCompanyById(int companyId) {
        return companyRepo.findById(companyId); // findById returns an Optional
    }

    // Method to update a company
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


}
