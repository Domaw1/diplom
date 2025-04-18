package ru.degree.shop.DTO.order;

import lombok.Data;
import ru.degree.shop.DTO.product.ProductVariantGetDto;

@Data
public class OrderItemGetDto {
    private Long id;
    private Long order;
    private ProductVariantGetDto productVariant;
    private Integer quantity;
}
