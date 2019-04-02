package com.example.demo.controllers;

import com.example.demo.FormResponds.SignUpData;
import com.example.demo.models.UserProfile;
import com.example.demo.repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    private final UserRepository userRepository;

    public SignUpController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public SignUpData login(@RequestBody SignUpData user){
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(user.getUsername());
        userProfile.setPassword(user.getPassword());
        userRepository.save(userProfile);
        return user;

    }


}
