package ru.stepup.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account")
    private String account;

    @Column(name = "balance")
    private BigDecimal balance;

    @Convert(converter = ProductTypeConverter.class)
    @Column(name = "product_type")
    private ProductType product_type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
