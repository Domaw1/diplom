package ru.degree.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.degree.shop.DTO.cart.CartGetDto;
import ru.degree.shop.model.Cart;
import ru.degree.shop.model.User;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {

    @Mapping(target = "user", source = "user", qualifiedByName = "mapUser")
    CartGetDto toCartItemGetDto(Cart cart);

    @Named("mapUser")
    default String mapUser(User user) {
        return user.getUsername();
    }
}
