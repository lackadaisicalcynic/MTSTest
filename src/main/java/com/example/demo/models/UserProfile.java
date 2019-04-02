package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    @Column(unique = true)
    String username;
    String password;
}
