package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.WishList;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    Optional<WishList> findWishListByUser_Id(Long userId);

    Optional<WishList> findWishListByUser_Email(String userEmail);
}
