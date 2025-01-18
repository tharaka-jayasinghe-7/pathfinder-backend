package com.example.pathfinder.Data.AdminData;

public class AdminResponse {

    private int adminId;
    private String email;

    public AdminResponse(int adminId, String email) {
        this.adminId = adminId;
        this.email = email;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
