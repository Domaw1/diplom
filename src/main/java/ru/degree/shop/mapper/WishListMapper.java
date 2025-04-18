package ru.degree.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.degree.shop.DTO.wishlist.WishListUserGetDto;
import ru.degree.shop.model.User;
import ru.degree.shop.model.WishList;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface WishListMapper {

    @Mapping(target = "user", source = "user", qualifiedByName = "mapUser")
    WishListUserGetDto toWishListUserGetDto(WishList wishList);

    @Named("mapUser")
    default String toWishListUserGetDto(User user) {
        return user.getUsername();
    }
}
