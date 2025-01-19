package com.example.pathfinder.Data.PostData;

import java.time.LocalDate;
import java.util.Date;

public class PostDTO {
    private int id;
    private String title;
    private String content;
    private Date date;
    private int companyId;
    private String companyName; // Additional field

    public PostDTO(Post post) {
        this.id = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.date = post.getDate();
        this.companyId = post.getCompany().getCompanyId();
        this.companyName = post.getCompany().getCompanyName(); // Include companyName
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
