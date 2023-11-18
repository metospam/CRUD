package com.example.project.controller;

import com.example.project.model.Product;
import com.example.project.repository.ProductRepository;
import com.example.project.service.ProductService;
import com.example.project.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final StorageService storageService;
    private final ProductService productService;

    @GetMapping
    public String index(@RequestParam(required = false) String query, Model model,
                        @PageableDefault(size = 5, sort = {"id"},
                                direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Product> products = productService.getAllProducts(query, pageable);

        model.addAttribute("products", products);
        model.addAttribute("query", query);

        return "products";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());

        return "create_product";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Product product,
                         @RequestParam MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {
            String fileName = storageService.store(imageFile);
            product.setImagePath("/images/" + fileName);
        }

        productRepository.save(product);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        model.addAttribute("product", product);

        return "edit_product";
    }

    @PostMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,
                               @ModelAttribute Product product) {
        productRepository.save(product);

        return "redirect:/edit/" + id;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam List<Long> selectedProducts) {

        if (!selectedProducts.isEmpty()) {
            productService.deleteProducts(selectedProducts);
        }

        return "redirect:/";
    }
}
