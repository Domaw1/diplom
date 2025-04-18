package ru.degree.shop.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.wishlist.WishListUserGetDto;
import ru.degree.shop.DTO.wishlist.WishListUserDto;
import ru.degree.shop.exception.NotFoundException;
import ru.degree.shop.mapper.WishListMapper;
import ru.degree.shop.model.Product;
import ru.degree.shop.model.User;
import ru.degree.shop.model.WishList;
import ru.degree.shop.repository.ProductRepository;
import ru.degree.shop.repository.UserRepository;
import ru.degree.shop.repository.WishListRepository;
import ru.degree.shop.service.WishListService;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class WishListServiceImpl implements WishListService {
    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final WishListMapper wishListMapper;

    @Override
    public WishListUserGetDto getUserWishList(String email) {
        WishList wishList = wishListRepository.findWishListByUser_Email(email)
                .orElseThrow(() -> new NotFoundException("WishList not found"));

        return wishListMapper.toWishListUserGetDto(wishList);
    }

    @Override
    @Transactional
    public WishListUserGetDto addToUserWishList(WishListUserDto wishListUserPostDto, String email) {
        Product product = productRepository.findById(wishListUserPostDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        WishList wishList = wishListRepository.findWishListByUser_Email(email)
                .orElseGet(WishList::new);

        wishList.setProducts(wishList.getProducts() != null ? wishList.getProducts() : new ArrayList<>());
        wishList.setUser(user);

        if(!wishList.getProducts().contains(product)) {
            wishList.getProducts().add(product);
            wishListRepository.save(wishList);
        }
        return wishListMapper.toWishListUserGetDto(wishList);
    }

    @Override
    @Transactional
    public WishListUserGetDto deleteFromUserWishList(WishListUserDto wishListDeleteDto, String email) {
        WishList wishList = wishListRepository.findWishListByUser_Email(email)
                .orElseThrow(() -> new NotFoundException("WishList not found"));

        Product product = productRepository.findById(wishListDeleteDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        wishList.getProducts().remove(product);
        wishListRepository.save(wishList);

        if(wishList.getProducts().isEmpty()) {
            wishListRepository.delete(wishList);
            return null;
        }
        return wishListMapper
                .toWishListUserGetDto(wishList);
    }
}
