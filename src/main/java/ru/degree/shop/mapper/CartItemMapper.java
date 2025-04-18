package ru.degree.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.degree.shop.DTO.cart.CartItemGetDto;
import ru.degree.shop.model.Cart;
import ru.degree.shop.model.CartItem;

@Mapper(componentModel = "spring", uses = {ProductVariantMapper.class})
public interface CartItemMapper {

    @Mapping(target = "cart", source = "cart", qualifiedByName = "mapCart")
    CartItemGetDto cartItemToCartItemGetDto(CartItem cartItem);

    @Named("mapCart")
    default Long mapCart(Cart cart) {
        return cart.getId();
    }
}
