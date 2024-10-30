package ru.stepup.dto;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProductTypeConverter implements AttributeConverter<ProductType, String> {

    @Override
    public String convertToDatabaseColumn(ProductType attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public ProductType convertToEntityAttribute(String dbData) {
        return dbData == null ? null : ProductType.fromCode(dbData);
    }

}
