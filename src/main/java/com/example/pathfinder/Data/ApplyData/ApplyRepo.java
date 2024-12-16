package com.example.pathfinder.Data.ApplyData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepo extends JpaRepository<Apply, Integer> {
}
