package com.example.demo.FormResponds;

import com.example.demo.models.Role;
import lombok.Data;

import javax.persistence.Enumerated;

@Data
public class SignUpData {
    String username;
    String password;
    @Enumerated
    Role role;
}
