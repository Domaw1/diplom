package ru.degree.shop.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.cart.CartGetDto;
import ru.degree.shop.DTO.cart.CartItemDeleteDto;
import ru.degree.shop.DTO.cart.CartPostDto;
import ru.degree.shop.exception.NotFoundException;
import ru.degree.shop.mapper.CartMapper;
import ru.degree.shop.model.Cart;
import ru.degree.shop.model.CartItem;
import ru.degree.shop.model.ProductVariant;
import ru.degree.shop.model.User;
import ru.degree.shop.repository.CartItemRepository;
import ru.degree.shop.repository.CartRepository;
import ru.degree.shop.repository.ProductVariantRepository;
import ru.degree.shop.repository.UserRepository;
import ru.degree.shop.service.CartService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductVariantRepository productVariantRepository;
    private final CartMapper cartMapper;

    @Override
    public CartGetDto getCartByUserEmail(String email) {
        Cart cart = cartRepository.findCartByUser_Email(email)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        return cartMapper.toCartItemGetDto(cart);
    }

    @Override
    @Transactional
    public CartGetDto addToUserCart(CartPostDto cartPostDto, String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        ProductVariant productVariant = productVariantRepository.findById(cartPostDto.getProductVariantId())
                .orElseThrow(() -> new NotFoundException("Product variant not found"));

        Cart cart = cartRepository.findCartByUser_Email(email)
                .orElseGet(Cart::new);

        if (cart.getUser() == null) {
            cart.setUser(user);
        }

        CartItem existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProductVariant().equals(productVariant))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + cartPostDto.getQuantity());
            return cartMapper.toCartItemGetDto(cart);
        }

        return addCartItemToCart(cartPostDto, productVariant, cart);
    }

    @Override
    @Transactional
    public CartGetDto removeFromUserCart(CartItemDeleteDto cartItemDeleteDto, String email) {
        Cart cart = cartRepository.findCartByUser_Email(email)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        CartItem cartItem = cart.getCartItems()
                .stream().filter(item -> item.getId().equals(cartItemDeleteDto.getProductVariantId()))
                .findFirst().orElse(null);

        if(cartItem == null)
            throw new NotFoundException("Cart item not found");

        if (cartItemDeleteDto.getQuantity() != null && cartItemDeleteDto.getQuantity() >= cartItem.getQuantity()) {
            cart.getCartItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        } else if (cartItemDeleteDto.getQuantity() != null) {
            cartItem.setQuantity(cartItem.getQuantity() - cartItemDeleteDto.getQuantity());
        } else {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
            } else {
                cart.getCartItems().remove(cartItem);
                cartItemRepository.delete(cartItem);
            }
        }

        return cartMapper.toCartItemGetDto(cart);
    }

    @Override
    public String removeUserCart(String email) {
        Cart cart = cartRepository.findCartByUser_Email(email)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        List<CartItem> itemsToDelete = new ArrayList<>(cart.getCartItems());

        cart.getCartItems().clear();
        cartRepository.save(cart);

        cartItemRepository.deleteAll(itemsToDelete);
        return "Deleted Cart";
    }

    private CartGetDto addCartItemToCart(CartPostDto cartPostDto, ProductVariant productVariant, Cart cart) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProductVariant(productVariant);
        cartItem.setQuantity(cartPostDto.getQuantity());
        if(cart.getCartItems() == null)
            cart.setCartItems(new ArrayList<>());
        cart.getCartItems().add(cartItem);
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toCartItemGetDto(savedCart);
    }
}
