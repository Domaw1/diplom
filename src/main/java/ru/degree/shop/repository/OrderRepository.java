package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findAllByUser_Id(Long userId);

    Optional<List<Order>> findOrderByIdAndUser_Email(Long id, String userEmail);

    Optional<List<Order>> findAllByUser_EmailOrderByCreatedAtDesc(String userEmail);
}
