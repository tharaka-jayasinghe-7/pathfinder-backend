package com.example.pathfinder.Controller.ApplyController;

import com.example.pathfinder.Data.ApplyData.Apply;
import com.example.pathfinder.Data.ApplyData.ApplyDTO;
import com.example.pathfinder.Service.ApplyService.ApplyService;
import com.example.pathfinder.Service.JobService.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @PostMapping("/user/{userId}/job/{jobId}/addApply")
    public ResponseEntity<Apply> addApply(
            @PathVariable int userId,
            @PathVariable int jobId,
            @RequestParam("cvImage") MultipartFile file) throws IOException, SQLException {

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        Apply apply = new Apply();
        apply.setCvImage(blob);

        Apply savedApply = applyService.addApply(apply, userId, jobId);

        return new ResponseEntity<>(savedApply, HttpStatus.CREATED);
    }

    @GetMapping("/getApply/{applyId}")
    public ResponseEntity<Apply> getApplyById(@PathVariable int applyId) {
        Optional<Apply> apply = applyService.getApplyById(applyId);
        if (apply.isPresent()) {
            return new ResponseEntity<>(apply.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getApplies")
    public List<Apply> getApplys() {
        List<Apply> applies = applyService.getAllApply();
        return applies;
    }

    @DeleteMapping("/deleteApply/{applyId}")
    public void deleteApply(@PathVariable int applyId) {
        applyService.deleteApply(applyId);
    }

    @GetMapping("/getAppliesByUser/{userId}")
    public ResponseEntity<List<ApplyDTO>> getAppliesByUser(@PathVariable int userId) {
        List<ApplyDTO> applies = applyService.getAppliesByUserId(userId);
        if (applies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(applies, HttpStatus.OK);
    }

    @GetMapping("/getApplyByJob/{jobId}")
    public ResponseEntity<List<Apply>> getApplyByJob(@PathVariable int jobId){
        List<Apply> applyList = applyService.getApplyByJobId(jobId);
        return new ResponseEntity<>(applyList, HttpStatus.OK);
    }


}
