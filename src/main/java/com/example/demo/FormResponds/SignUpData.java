package com.example.demo.FormResponds;

import com.example.demo.models.Role;
import lombok.Data;

import javax.persistence.Enumerated;

@Data
public class SignUpData {
    private String username;
    private String password;
    @Enumerated
    private Role role;
    private String name;
}
