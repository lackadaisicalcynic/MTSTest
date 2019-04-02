package com.example.demo.controllers;

import com.example.demo.FormResponds.SignUpData;
import com.example.demo.models.UserProfile;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public SignUpController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public SignUpData login(@RequestBody SignUpData user){
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(user.getUsername());
        userProfile.setPassword(passwordEncoder.encode(user.getPassword()));
        userProfile.setRole(user.getRole());
        userRepository.save(userProfile);
        return user;

    }


}
