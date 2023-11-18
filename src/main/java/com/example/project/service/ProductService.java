package com.example.project.service;

import com.example.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    void deleteProducts(List<Long> selectedProducts);
    Page<Product> getAllProducts(String query, Pageable pageable);
}
