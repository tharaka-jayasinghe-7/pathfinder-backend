package com.example.pathfinder.Service.PostService;

import com.example.pathfinder.Data.PostData.Post;
import com.example.pathfinder.Data.PostData.PostRepo;
import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Service.CompanyService.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CompanyService companyService;

    // Add Post
    public Post addPost(Post post, int companyId) {
        Optional<Company> company = companyService.getCompanyById(companyId);
        if (company.isPresent()) {
            post.setCompany(company.get());
            return postRepo.save(post);
        }
        throw new RuntimeException("Company not found.");
    }

    // Get All Posts
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    // Get Post by ID
    public Optional<Post> getPostById(int postId) {
        return postRepo.findById(postId);
    }

    // Update Post
    public Post updatePost(int postId, Post postDetails) {
        Optional<Post> existingPost = postRepo.findById(postId);
        if (existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            updatedPost.setTitle(postDetails.getTitle());
            updatedPost.setContent(postDetails.getContent());
            updatedPost.setImage(postDetails.getImage());
            updatedPost.setType(postDetails.getType());
            updatedPost.setDate(postDetails.getDate());
            updatedPost.setCompany(postDetails.getCompany());
            return postRepo.save(updatedPost);
        }
        return null; // Or throw an exception
    }

    // Delete Post
    public String deletePostById(int postId) {
        Optional<Post> existingPost = postRepo.findById(postId);
        if (existingPost.isPresent()) {
            postRepo.delete(existingPost.get());
            return "Post deleted successfully.";
        }
        return "Post not found.";
    }

    // Get Posts by Company ID
    public List<Post> getPostsByCompanyId(int companyId) {
        return postRepo.findByCompany_CompanyId(companyId);
    }
}
