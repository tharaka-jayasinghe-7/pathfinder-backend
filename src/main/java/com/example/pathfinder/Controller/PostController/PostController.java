package com.example.pathfinder.Controller.PostController;

import com.example.pathfinder.Data.PostData.Post;
import com.example.pathfinder.Service.PostService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("company/{companyId}/addpost")
    public ResponseEntity<Post> addPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("type") String type,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam("image") MultipartFile file,
            @PathVariable int companyId) throws IOException, SQLException {

        // Convert the uploaded file to a byte array and create a Blob
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        // Create a new Post object and set its fields
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setType(type);
        post.setDate(date);
        post.setImage(blob);

        // Save the post using the service layer
        Post savedPost = postService.addPost(post, companyId);

        // Return the saved post object with a 201 (Created) status
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/getpost/{postId}")
    public ResponseEntity<Post> getPostById (@PathVariable int postId){
        Optional<Post> post = postService.getPostById(postId);

        if(post.isPresent()){
            return new ResponseEntity<>(post.get(), HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("getposts")
    public List<Post> getAllPost(){
        List<Post> posts = postService.getPosts();
        return posts;
    }

    @DeleteMapping("/deletepost/{postId}")
    public void deletePost(@PathVariable int postId){
        postService.deletePost(postId);
    }
}
