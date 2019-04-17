package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class ItemProfile {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @NotNull
   @Column(unique=true)
   private String name;
   @NotNull
   private String category;
   private int price;
   private int amount;
   private String description;
}
