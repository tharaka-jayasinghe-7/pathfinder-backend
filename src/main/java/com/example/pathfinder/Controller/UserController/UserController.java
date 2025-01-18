package com.example.pathfinder.Controller.UserController;

import com.example.pathfinder.Data.JobData.Job;
import com.example.pathfinder.Data.UserData.User;
import com.example.pathfinder.Data.UserData.UserResponse;
import com.example.pathfinder.Service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@ModelAttribute User user, @RequestParam("userImage") MultipartFile file)
            throws IOException, SQLException {
        // Handle the file upload
        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        user.setImage(blob);

        // Save the user and get the saved user object
        User savedUser = userService.addUser(user);

        // Return the saved user object with a 201 status code (Created)
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int userId) {
        Optional<User> user = userService.getUserById(userId);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = userService.getUsers();
        return users;
    }

    @GetMapping("/getUserByEmail/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/userLogin")
    public UserResponse login(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}/image")
    public ResponseEntity<byte[]> getImageByJobId(@PathVariable int userId) {
        Optional<User> user = userService.getUserById(userId);

        if (user.isPresent()) {
            try {
                Blob blob = user.get().getImage(); // Assuming this returns a Blob
                byte[] image = blob.getBytes(1, (int) blob.length()); // Convert Blob to byte[]
                return new ResponseEntity<>(image, HttpStatus.OK);
            } catch (SQLException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Handle SQL exceptions
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
