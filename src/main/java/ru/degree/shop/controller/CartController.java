package ru.degree.shop.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.degree.shop.DTO.cart.CartGetDto;
import ru.degree.shop.DTO.cart.CartItemDeleteDto;
import ru.degree.shop.DTO.cart.CartPostDto;
import ru.degree.shop.service.CartService;

@RestController
@RequestMapping("api/v1/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/user")
    public ResponseEntity<CartGetDto> getCartByUserID(Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(cartService.getCartByUserEmail(email), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<CartGetDto> addProductToUserCart(@RequestBody @Valid CartPostDto cartPostDto,
                                                           Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(cartService.addToUserCart(cartPostDto, email), HttpStatus.CREATED);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(cartService.removeUserCart(email), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<CartGetDto> deleteProductFromUserCart(@RequestBody CartItemDeleteDto cartItemDeleteDto,
                                                                Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(cartService.removeFromUserCart(cartItemDeleteDto, email), HttpStatus.OK);
    }
}
