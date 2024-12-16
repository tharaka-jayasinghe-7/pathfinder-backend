package com.example.pathfinder.Service.CourseService;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CourseData.Course;
import com.example.pathfinder.Data.CourseData.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Course updateCourse(int courseId, Course courseDetails) {
        Optional<Course> existingCourse = courseRepo.findById(courseId);
        if (existingCourse.isPresent()) {
            Course updatedCourse = existingCourse.get();
            updatedCourse.setCourseName(courseDetails.getCourseName());
            updatedCourse.setInstitute(courseDetails.getInstitute());
            updatedCourse.setWebUrl(courseDetails.getWebUrl());
            updatedCourse.setIndustry(courseDetails.getIndustry());
            updatedCourse.setMinAgeLimit(courseDetails.getMinAgeLimit());
            updatedCourse.setMaxAgeLimit(courseDetails.getMaxAgeLimit());
            updatedCourse.setReqOlPassCount(courseDetails.getReqOlPassCount());
            updatedCourse.setDuration(courseDetails.getDuration());
            updatedCourse.setNvqLevel(courseDetails.getNvqLevel());
            updatedCourse.setType(courseDetails.getType());
            updatedCourse.setImage(courseDetails.getImage());



            return courseRepo.save(updatedCourse);
        }
        return null;
    }

    public String deleteCourseById(int courseId) {
        Optional<Course> existingCourse = courseRepo.findById(courseId);
        if (existingCourse.isPresent()) {
            courseRepo.delete(existingCourse.get());
            return "Course deleted successfully.";
        }
        return "Course not found.";
    }
}
