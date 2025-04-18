package ru.degree.shop.DTO.cart;

import lombok.Data;

@Data
public class CartItemDeleteDto {
    Long productVariantId;
    Integer quantity;
}
