package ru.stepup.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.dto.Product;
import ru.stepup.dto.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    @NonNull
    Optional<Product> findById(@NonNull Long id);
    Product findByUserAndAccount(User user, String account);
    List<Product> findByUserIdAndAccount(Long userId, String account);
    List<Product> findByUserId(Long userId);
}
