package com.example.pathfinder.Data.CourseData;


import com.example.pathfinder.Data.AdminData.Admin;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;
    private String institute;
    private String webUrl;
    private String industry;
    private String minAgeLimit;
    private String maxAgeLimit;
    private int reqOlPassCount;
    private String duration;
    private String nvqLevel;
    private String type;
    @Lob
    private Blob image;



    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMinAgeLimit() {
        return minAgeLimit;
    }

    public void setMinAgeLimit(String minAgeLimit) {
        this.minAgeLimit = minAgeLimit;
    }

    public String getMaxAgeLimit() {
        return maxAgeLimit;
    }

    public void setMaxAgeLimit(String maxAgeLimit) {
        this.maxAgeLimit = maxAgeLimit;
    }

    public int getReqOlPassCount() {
        return reqOlPassCount;
    }

    public void setReqOlPassCount(int reqOlPassCount) {
        this.reqOlPassCount = reqOlPassCount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNvqLevel() {
        return nvqLevel;
    }

    public void setNvqLevel(String nvqLevel) {
        this.nvqLevel = nvqLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
