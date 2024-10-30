package ru.stepup.service;

import org.springframework.stereotype.Service;
import ru.stepup.dto.Product;
import ru.stepup.repository.ProductDao;

import java.util.List;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product findById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    public List<Product> findByUserId(Long userId) {
        return productDao.findByUserId(userId);
    }

    public List<Product> findByUserIdAndAccount(Long userId, String account) {
        return productDao.findByUserIdAndAccount(userId, account);
    }
}
