package ru.degree.shop.DTO.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductCreateDto {
    private String name;
    private String description;
    private BigDecimal price;
    private String gender;
    private String brand;
    private String subCategory;
    private List<String> imageUrls;
    private List<ProductVariantCreateDto> productVariants;
}

