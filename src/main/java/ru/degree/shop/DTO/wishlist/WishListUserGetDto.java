package ru.degree.shop.DTO.wishlist;

import lombok.Data;
import ru.degree.shop.DTO.product.ProductGetDto;

import java.util.List;

@Data
public class WishListUserGetDto {
    private String user;
    private List<ProductGetDto> products;
}
