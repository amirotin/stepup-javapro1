package ru.stepup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepup.dto.PaymentRequestDto;
import ru.stepup.dto.ProductDto;
import ru.stepup.service.ProductsService;

import java.util.List;

@RestController
@RequestMapping("/paycore/v1")
public class PaymentController {

    private final ProductsService productsService;

    public PaymentController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products/{userId}")
    public ResponseEntity<?> getProducts(@PathVariable Long userId) {
        List<ProductDto> products = productsService.getProducts(userId);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/execute")
    public String executePayment(@RequestBody PaymentRequestDto paymentRequest) {
        return productsService.executePayment(paymentRequest);
    }

}