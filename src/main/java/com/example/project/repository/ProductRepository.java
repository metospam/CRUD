package com.example.project.repository;

import com.example.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    Page<Product> findAll(String query, Pageable pageable);

    Page<Product> findAll(Pageable pageable);
}
