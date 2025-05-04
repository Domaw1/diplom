package ru.degree.shop.DTO.product;

import lombok.Data;

@Data
public class ProductVariantCreateDto {
    private String size;
    private String color;
    private Integer quantity;
}
