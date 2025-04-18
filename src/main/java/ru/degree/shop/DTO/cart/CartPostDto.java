package ru.degree.shop.DTO.cart;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartPostDto {
    private Long productVariantId;
    @Positive
    private Integer quantity;
}
