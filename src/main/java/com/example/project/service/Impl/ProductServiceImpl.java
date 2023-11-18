package com.example.project.service.Impl;

import com.example.project.model.Product;
import com.example.project.repository.ProductRepository;
import com.example.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void deleteProducts(List<Long> selectedProducts) {
        for (Long productId : selectedProducts){
            productRepository.deleteById(productId);
        }
    }

    @Override
    public Page<Product> getAllProducts(String query, Pageable pageable) {
        Page<Product> products;

        if (query != null && !query.isEmpty()) {
            products = productRepository.findAll(query, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        return products;
    }

}
