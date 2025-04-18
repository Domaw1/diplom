package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
