package com.example.pathfinder.Controller.CourseController;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CourseData.Course;
import com.example.pathfinder.Service.CourseService.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    @Autowired
    private CourseService courseService;



    @PostMapping("/addcourse")
    public ResponseEntity<Course> addCourse(@ModelAttribute Course course, @RequestParam("profilePic") MultipartFile file)
            throws IOException, SQLException {

        byte[] bytes = file.getBytes();

        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        course.setImage(blob);


        Course savedCourse = courseService.addCourse(course);


        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    // Build Course GetAll REST API
    @GetMapping("/getcourses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PutMapping("/updatecourse/{courseId}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable int courseId,
            @ModelAttribute Course course,
            @RequestParam("profilePic") MultipartFile file) throws IOException, SQLException {

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        course.setImage(blob);

        Course updatedCourse = courseService.updateCourse(courseId, course);

        if (updatedCourse != null) {
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletecourse/{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable int courseId) {
        String message = courseService.deleteCourseById(courseId);

        if (message.equals("Course deleted successfully.")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
