package com.example.pathfinder.Data.InterviewData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepo extends JpaRepository<Interview, Integer> {

    @Query("SELECT i FROM Interview i " +
            "JOIN FETCH i.company c " +
            "JOIN FETCH i.job j " +
            "JOIN i.users u " + // Using 'i.users' to join with the User entity
            "WHERE u.userId = :userId")
    List<Interview> findInterviewsWithCompanyAndJobByUserId(@Param("userId") int userId);


}
