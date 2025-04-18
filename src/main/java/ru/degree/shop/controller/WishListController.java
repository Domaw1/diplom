package ru.degree.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.degree.shop.DTO.wishlist.WishListUserGetDto;
import ru.degree.shop.DTO.wishlist.WishListUserDto;
import ru.degree.shop.service.WishListService;

@RestController
@RequestMapping("api/v1/wishlist")
@AllArgsConstructor
public class WishListController {
    private final WishListService wishListService;

    @GetMapping("/user")
    public ResponseEntity<WishListUserGetDto> getUserWishList(Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(wishListService.getUserWishList(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WishListUserGetDto> addProductToUserWishList(@RequestBody WishListUserDto wishListUserPostDto,
                                                                       Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(wishListService.addToUserWishList(wishListUserPostDto, email), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<WishListUserGetDto> deleteProductFromUserWishList(@RequestBody WishListUserDto wIshListDeleteDto,
                                                                            Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(wishListService.deleteFromUserWishList(wIshListDeleteDto, email), HttpStatus.OK);
    }
}
