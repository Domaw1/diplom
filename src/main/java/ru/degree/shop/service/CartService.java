package ru.degree.shop.service;

import ru.degree.shop.DTO.cart.CartGetDto;
import ru.degree.shop.DTO.cart.CartItemDeleteDto;
import ru.degree.shop.DTO.cart.CartPostDto;

public interface CartService {
    CartGetDto getCartByUserEmail(String email);

    CartGetDto addToUserCart(CartPostDto cartPostDto, String email);

    CartGetDto removeFromUserCart(CartItemDeleteDto cartItemDeleteDto, String email);

    String removeUserCart(String email);
}
