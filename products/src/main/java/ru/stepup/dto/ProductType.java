package ru.stepup.dto;

import lombok.Getter;

@Getter
public enum ProductType {
    CARD("0", "Card"),
    ACCOUNT("1", "Account");

    private final String code;
    private final String desc;

    ProductType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ProductType fromCode(String code) {
        for (ProductType type : ProductType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
