package com.example.pathfinder.Service.InterviewService;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CompanyData.CompanyRepo;
import com.example.pathfinder.Data.InterviewData.Interview;
import com.example.pathfinder.Data.InterviewData.InterviewRepo;
import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.JobData.JobRepo;
import com.example.pathfinder.Data.UserData.User;
import com.example.pathfinder.Data.UserData.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {

    @Autowired
    InterviewRepo interviewRepo;

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    JobRepo jobRepo;
    @Autowired
    private UserRepo userRepo;

    public Interview addInterview(Interview interview, int companyId, int jobId) {
        Company company = companyRepo.findById(companyId).orElseThrow(()-> new RuntimeException("Company not found"));
        Job job = jobRepo.findById(jobId).orElseThrow(()-> new RuntimeException("Job not found"));
        interview.setCompany(company);
        interview.setJob(job);
        return interviewRepo.save(interview);
    }

    @Transactional
    public Interview addUSersToInterview (int InterviewId, List<Integer> userIds){

        Interview interview = interviewRepo.findById(InterviewId).orElseThrow(()-> new RuntimeException("Interview not found"));

        List<User> users = userRepo.findAllById(userIds);

        if(users.isEmpty()){
            throw new RuntimeException("Users not found");
        }

        // Update both sides of the relationship
        for (User user : users) {
            // Add interview to user's list
            user.getInterviews().add(interview); // Update owning side
        }

        interview.getUsers().addAll(users);

        userRepo.saveAll(users);
        return interviewRepo.save(interview);
    }

    public List<Interview> getAllInterviews(){
        return interviewRepo.findAll();
    }

    public Optional<Interview> getInterviewById(int interviewId){
        return interviewRepo.findById(interviewId);
    }
}
