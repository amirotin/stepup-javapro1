package ru.stepup.dto;

import java.math.BigDecimal;

public record PaymentRequestDto(
        Long payer,
        String account,
        BigDecimal amount
) {
}
