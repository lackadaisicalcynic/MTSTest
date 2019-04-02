package com.example.demo.repositories;

import com.example.demo.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long>{
    Optional<UserProfile> findByUsername(String username);

    Boolean existsByUsername(String email);

}
