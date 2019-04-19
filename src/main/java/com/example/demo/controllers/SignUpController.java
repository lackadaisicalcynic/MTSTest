package com.example.demo.controllers;

import com.example.demo.FormResponds.SignUpData;
import com.example.demo.models.UserProfile;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SignUpController {
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);


    public SignUpController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public UserProfile login(@RequestBody SignUpData user){
        logger.info("Signing up new user with username: " + user.getUsername());
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(user.getUsername());
        userProfile.setPassword(passwordEncoder.encode(user.getPassword()));
        userProfile.setRole(user.getRole());
        userRepository.save(userProfile);
        logger.info("Signed up");
        return userProfile;

    }
    @PatchMapping("/updateprofile/{username}")
    public SignUpData update(@PathVariable String username, @RequestBody SignUpData user){
        UserProfile updatedUser = userRepository.findByUsername(username).get();
        logger.info("Updating profile of user with username: " + username);
        if (updatedUser == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user does not exist");
        }
        if (user.getUsername() != null){
            updatedUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null){
            updatedUser.setPassword(user.getPassword());
        }
        if (user.getRole() !=  null){
            updatedUser.setRole(user.getRole());
        }
        userRepository.save(updatedUser);
        logger.info("Updated");
        return user;
    }


}
