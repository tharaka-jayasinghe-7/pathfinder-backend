package com.example.pathfinder.Data.PostData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    Optional<Post> findById(int postId);
    List<Post> findByCompany_CompanyId(int companyId); // To find posts by company ID
}
