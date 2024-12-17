package com.example.pathfinder.Service.PostService;

import com.example.pathfinder.Data.CompanyData.Company;
import com.example.pathfinder.Data.CompanyData.CompanyRepo;
import com.example.pathfinder.Data.PostData.Post;
import com.example.pathfinder.Data.PostData.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CompanyRepo companyRepo;

    public Post addPost(Post post, int companyId){
        Company company = companyRepo.findById(companyId).orElseThrow(() -> new RuntimeException("Company not found"));
        post.setCompany(company);
        return postRepo.save(post);
    }

    public Optional<Post> getPostById(int id) {
        return postRepo.findById(id);
    }

    public List<Post> getPosts(){
        return postRepo.findAll();
    }

    public void deletePost(int postId){
        postRepo.deleteById(postId);
    }

    public Post updatePost(int postId, int companyId, Post updatedPost) {
        Post existingPost = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));


        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + companyId));

        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        existingPost.setType(updatedPost.getType());
        existingPost.setDate(updatedPost.getDate());
        existingPost.setCompany(company);

        if (updatedPost.getImage() != null) {
            existingPost.setImage(updatedPost.getImage());
        }

        return postRepo.save(existingPost);
    }

}
