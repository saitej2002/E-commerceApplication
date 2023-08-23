package com.example.Image.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
    @Entity
    @Getter
@Setter
    @Table(name = "productt")
    public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private String imageName;

        private String description;
        private double price;
    }
