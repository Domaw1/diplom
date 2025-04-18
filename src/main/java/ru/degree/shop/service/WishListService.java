package ru.degree.shop.service;

import ru.degree.shop.DTO.wishlist.WishListUserGetDto;
import ru.degree.shop.DTO.wishlist.WishListUserDto;

public interface WishListService {
    WishListUserGetDto getUserWishList(String email);
    WishListUserGetDto addToUserWishList(WishListUserDto wishListUserPostDto, String email);
    WishListUserGetDto deleteFromUserWishList(WishListUserDto wishListDeleteDto, String email);
}
