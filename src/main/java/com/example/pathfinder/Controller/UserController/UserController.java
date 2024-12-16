package com.example.pathfinder.Controller.UserController;

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
    public ResponseEntity<Object> userLogin(@RequestBody User loginUser) {
        try {
            User user = userService.authenticateUser(loginUser.getEmail(), loginUser.getPassword());

            if(user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Email or Password");
            }
            UserResponse response = new UserResponse(user.getUserId(),user.getEmail());
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

}
