package ru.degree.shop.DTO.cart;

import lombok.Data;
import ru.degree.shop.DTO.product.ProductVariantGetDto;

@Data
public class CartItemGetDto {
    private Long id;
    private Long cart;
    private ProductVariantGetDto productVariant;
    private Integer quantity;
}
