package com.example.pathfinder.Service.UserService;

import com.example.pathfinder.Data.UserData.User;
import com.example.pathfinder.Data.UserData.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public User authenticateUser(String email, String password) {
        User user = userRepo.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void deleteUser(int userId) {
        userRepo.deleteById(userId);
    }

}
