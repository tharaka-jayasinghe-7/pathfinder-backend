package com.example.pathfinder.Controller.UserController;

import com.example.pathfinder.Data.UserData.User;
import com.example.pathfinder.Service.UserService.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

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




}
