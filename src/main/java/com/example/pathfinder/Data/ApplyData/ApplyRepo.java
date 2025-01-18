package com.example.pathfinder.Data.ApplyData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRepo extends JpaRepository<Apply, Integer> {
    List<Apply> findByUser_UserId(int userId);

    List<Apply> findByJob_JobId(int jobId);
}
