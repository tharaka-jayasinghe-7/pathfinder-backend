package com.example.pathfinder.Service.UserService;

import com.example.pathfinder.Data.UserData.User;
import com.example.pathfinder.Data.UserData.UserRepo;
import com.example.pathfinder.Data.UserData.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> getUserById(int userId) {
        return userRepo.findById(userId);
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public UserResponse loginUser(String email, String password) {
        // Retrieve user by email
        User user = userRepo.findByEmail(email);

        // Check if the user exists and the password matches
        if (user != null && user.getPassword().equals(password)) {
            // Return a response with email and userId
            return new UserResponse(user.getUserId(), user.getEmail());
        } else {
            // Throw a 401 Unauthorized error when login fails
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
    }

    public void deleteUser(int userId) {
        userRepo.deleteById(userId);
    }

}
