package ru.stepup.dto;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        String account,
        BigDecimal balance,
        Long userId
) {
}
