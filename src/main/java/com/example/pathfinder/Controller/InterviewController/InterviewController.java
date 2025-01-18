package com.example.pathfinder.Controller.InterviewController;

import com.example.pathfinder.Data.InterviewData.Interview;
import com.example.pathfinder.Data.InterviewData.InterviewNotificationDTO;
import com.example.pathfinder.Data.InterviewData.InterviewRepo;
import com.example.pathfinder.Data.UserData.UserRepo;
import com.example.pathfinder.Service.InterviewService.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    @Autowired
    InterviewService interviewService;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private InterviewRepo interviewRepo;

    @PostMapping("/company/{companyId}/job/{jobId}/addInterview")
    public ResponseEntity<Interview> addInterview(
            @RequestParam("interviewDate") String interviewDateStr,
            @RequestParam("description") String description,
            @PathVariable int companyId,
            @PathVariable int jobId) throws IOException, SQLException, ParseException {

        // Parse the date manually
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date interviewDate = dateFormat.parse(interviewDateStr);

        Interview interview = new Interview();
        interview.setInterviewDate(interviewDate);
        interview.setDescription(description);

        Interview savedInterview = interviewService.addInterview(interview, companyId, jobId);
        return new ResponseEntity<>(savedInterview, HttpStatus.CREATED);
    }

    @PatchMapping("/interview/{interviewId}/addUsersToInterview")
    public ResponseEntity<Interview> addUsersToInterview(@PathVariable int interviewId, @RequestBody List<Integer> userIds){
        Interview updatedInterview = interviewService.addUSersToInterview(interviewId, userIds);
        return new ResponseEntity<>(updatedInterview, HttpStatus.CREATED);
    }

    @GetMapping("/getInterviews")
    public List<Interview> getInterviews(){
        return interviewService.getAllInterviews();
    }

    @GetMapping("/getInterviewById/{InterviewId}")
    public ResponseEntity<Interview> getInterviewById(@PathVariable int InterviewId){
        Optional<Interview> interview = interviewService.getInterviewById(InterviewId);

        return interview.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getNotificationsByUser/{userId}")
    public ResponseEntity<List<InterviewNotificationDTO>> getNotificationsByUser(@PathVariable int userId) {
        // Call the repository method with userId
        List<Interview> interviews = interviewRepo.findInterviewsWithCompanyAndJobByUserId(userId);

        if (interviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Map Interview entities to InterviewNotificationDTO
        List<InterviewNotificationDTO> notifications = interviews.stream()
                .map(interview -> new InterviewNotificationDTO(
                        interview.getInterviewId(),
                        interview.getInterviewDate().toString(),  // Convert Date to String if needed
                        interview.getDescription(),
                        interview.getCompany().getCompanyName(),
                        interview.getJob().getJobTitle()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(notifications);
    }
}
