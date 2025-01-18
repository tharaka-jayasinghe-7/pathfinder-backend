package com.example.pathfinder.Data.UserData;

import com.example.pathfinder.Data.InterviewData.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    // Add the custom query method to get interviews by userId
    @Query("SELECT i FROM Interview i JOIN i.users u WHERE u.userId = :userId")
    List<Interview> findInterviewsByUserId(@Param("userId") int userId);
}
