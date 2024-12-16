package com.example.pathfinder.Service.ApplyService;

import com.example.pathfinder.Data.ApplyData.Apply;
import com.example.pathfinder.Data.ApplyData.ApplyRepo;
import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.JobData.JobRepo;
import com.example.pathfinder.Data.UserData.User;
import com.example.pathfinder.Data.UserData.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplyService {

    @Autowired
    ApplyRepo applyRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    JobRepo jobRepo;

    public Apply addApply(Apply apply, int userId, int jobId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        apply.setUser(user);
        apply.setJob(job);
        return applyRepo.save(apply);
    }

    public Optional<Apply> getApplyById(int applyId) {
        return applyRepo.findById(applyId);
    }

    public List<Apply> getAllApply() {
        return applyRepo.findAll();
    }

    public void deleteApply(int applyId) {
        applyRepo.deleteById(applyId);
    }
}
