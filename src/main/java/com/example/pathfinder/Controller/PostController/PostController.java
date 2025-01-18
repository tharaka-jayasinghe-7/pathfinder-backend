package com.example.pathfinder.Controller.PostController;

import com.example.pathfinder.Data.PostData.Post;
import com.example.pathfinder.Service.PostService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    private PostService postService;

    // Add Post
    @PostMapping("/company/{companyId}/addPost")
    public ResponseEntity<Post> addPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("type") String type,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam("image") MultipartFile file,
            @PathVariable int companyId) throws IOException, SQLException {

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);


        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setType(type);
        post.setDate(date);
        post.setImage(blob);


        Post savedPost = postService.addPost(post, companyId);


        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }


    // Get All Posts
    @GetMapping("/getallposts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Get Post by ID
    @GetMapping("/getpost/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable int postId) {
        Optional<Post> post = postService.getPostById(postId);

        if (post.isPresent()) {
            return new ResponseEntity<>(post.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Post
    @PutMapping("/updatepost/{postId}")
    public ResponseEntity<Post> updatePost(
            @PathVariable int postId,
            @ModelAttribute Post post,
            @RequestParam("image") MultipartFile file) throws IOException, SQLException {

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        post.setImage(blob);

        Post updatedPost = postService.updatePost(postId, post);

        if (updatedPost != null) {
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Post
    @DeleteMapping("/deletepost/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable int postId) {
        String message = postService.deletePostById(postId);

        if (message.equals("Post deleted successfully.")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // Get Posts by Company ID
    @GetMapping("/getpostsbycompany/{companyId}")
    public ResponseEntity<List<Post>> getPostsByCompanyId(@PathVariable int companyId) {
        List<Post> posts = postService.getPostsByCompanyId(companyId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
