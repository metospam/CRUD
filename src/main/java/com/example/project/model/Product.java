package com.example.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_path")
    private String imagePath;

    @Column(nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer quantity = 0;

    @Column
    private String type = "Simple";
}
