package ru.degree.shop.DTO.product;

import lombok.Data;
import ru.degree.shop.DTO.review.ReviewGetDto;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductGetDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String gender;
    private String brand;
    private String subCategory;
    private Double rating;
    private List<ProductVariantGetDto> productVariants;
    private List<ReviewGetDto> reviews;
    private List<String> imageUrls;
}
