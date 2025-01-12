package com.example.pathfinder.Data.CompanyData;

public class CompanyResponse {

    private int companyId;
    private String email;

    public CompanyResponse(int companyId, String email) {
        this.companyId = companyId;
        this.email = email;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
