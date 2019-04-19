package com.example.demo.repositories;

import com.example.demo.models.ItemProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemProfile, Long> {
    List<ItemProfile> findAllByCategory(String category);
    ItemProfile findItemProfileByName(String name);
    void deleteItemProfileByName(String name);
    boolean existsByName(String name);
}
