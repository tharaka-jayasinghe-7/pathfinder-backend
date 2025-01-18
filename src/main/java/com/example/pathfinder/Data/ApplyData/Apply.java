package com.example.pathfinder.Data.ApplyData;

import com.example.pathfinder.Data.BlobSerializer.BlobSerializer;
import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.UserData.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.sql.Blob;

@Entity

public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applyId;
    @Lob
    @JsonSerialize(using = BlobSerializer.class)
    private Blob cvImage;

    @ManyToOne
    @JsonBackReference("user-apply")
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JsonBackReference("job-apply")
    @JoinColumn(name = "jobId")
    private Job job;

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public Blob getCvImage() {
        return cvImage;
    }

    public void setCvImage(Blob cvImage) {
        this.cvImage = cvImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
