package ru.stepup.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.stepup.dto.IntegrationException;
import ru.stepup.dto.PaymentRequestDto;
import ru.stepup.dto.ProductDto;

import java.util.List;

@Service
public class ProductsService {
    private final RestTemplate restTemplate;

    public ProductsService(@Qualifier("product-client") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductDto> getProducts(Long userId) {
        ProductDto[] products;
        try {
            products = restTemplate.getForObject("/products/v1/user/{userId}", ProductDto[].class, userId);
        } catch (Exception e) {
            throw new IntegrationException("Error while getting products", e);
        }
        if (products == null) {
            throw new IntegrationException("Error while getting products. Products are null", null);
        }
        return List.of(products);
    }

    public String executePayment(PaymentRequestDto paymentRequestDto) {
        try {
            ResponseEntity<ProductDto> response = restTemplate.postForEntity("/products/v1/user", paymentRequestDto, ProductDto.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new IntegrationException("Error while executing payment. Status code: " + response.getStatusCode(), null);
            }
            ProductDto product = response.getBody();
            if (product == null) {
                throw new IntegrationException("Error while executing payment. Product is null", null);
            }
            if (product.balance().compareTo(paymentRequestDto.amount()) < 0) {
                throw new IntegrationException("Error while executing payment. Not enough money", null);
            }
            return "OK";

        } catch (Exception e) {
            throw new IntegrationException("Error while executing payment", e);
        }

    }
}
