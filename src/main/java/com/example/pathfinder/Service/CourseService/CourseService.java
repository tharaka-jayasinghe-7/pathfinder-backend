package com.example.pathfinder.Service.CourseService;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CourseData.Course;
import com.example.pathfinder.Data.CourseData.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }
}
