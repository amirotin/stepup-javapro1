package ru.stepup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stepup.dto.Product;
import ru.stepup.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Product> getProductsByUserId(@PathVariable Long userId) {
        return productService.findByUserId(userId);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Product>> getProductsByUserIdAndAccount(Long userId, String account) {
        if (userId == null || account == null) {
            return ResponseEntity.badRequest().build();
        }
        if (account.isBlank()) {
            List<Product> products = productService.findByUserId(userId);
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.ok(productService.findByUserIdAndAccount(userId, account));
        }
    }
}