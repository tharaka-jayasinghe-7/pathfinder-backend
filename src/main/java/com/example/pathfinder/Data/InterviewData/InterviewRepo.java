package com.example.pathfinder.Data.InterviewData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepo extends JpaRepository<Interview, Integer> {
}
