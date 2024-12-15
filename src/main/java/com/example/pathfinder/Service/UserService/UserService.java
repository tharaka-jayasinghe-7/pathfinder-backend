package com.example.pathfinder.Service.UserService;

import com.example.pathfinder.Data.UserData.User;
import com.example.pathfinder.Data.UserData.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) {
        return userRepo.save(user);
    }

}
