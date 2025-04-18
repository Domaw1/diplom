package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.Cart;
import ru.degree.shop.model.User;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByUser_Id(Long userId);

    Optional<Cart> findCartByUser_Email(String userEmail);

    Long user(User user);
}
