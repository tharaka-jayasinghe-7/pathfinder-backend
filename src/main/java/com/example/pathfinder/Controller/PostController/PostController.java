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

    @PutMapping("/company/{companyId}/updatepost/{postId}")
    public ResponseEntity<Post> updatePost(
            @PathVariable int postId,
            @PathVariable int companyId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("type") String type,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam(value = "image", required = false) MultipartFile file) throws IOException, SQLException {

        Blob blob = null;
        if (file != null && !file.isEmpty()) {
            byte[] bytes = file.getBytes();
            blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        }

        Post updatedPost = new Post();
        updatedPost.setTitle(title);
        updatedPost.setContent(content);
        updatedPost.setType(type);
        updatedPost.setDate(date);
        if (blob != null) {
            updatedPost.setImage(blob);
        }

        Post savedPost = postService.updatePost(postId, companyId, updatedPost);

        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }


}
