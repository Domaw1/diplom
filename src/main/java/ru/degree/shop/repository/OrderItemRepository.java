package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
