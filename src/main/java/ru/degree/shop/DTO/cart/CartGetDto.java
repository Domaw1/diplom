package ru.degree.shop.DTO.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartGetDto {
    private Long id;
    private String user;
    private List<CartItemGetDto> cartItems;
}
