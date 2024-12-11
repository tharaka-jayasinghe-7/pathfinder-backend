package com.example.pathfinder.Data.CourseData;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    private String coursePic;

}
