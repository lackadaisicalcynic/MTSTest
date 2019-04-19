package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    @Column(unique = true)
    @NotNull
    String username;
    @NotNull
    String password;
    @Enumerated
    Role role;
    private String name;
}
