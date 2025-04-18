package ru.degree.shop.DTO.product;

import lombok.Data;

@Data
public class ProductVariantGetDto {
    private Long id;
    private ProductGetDto product;
    private String size;
    private String color;
    private Integer quantity;
}
