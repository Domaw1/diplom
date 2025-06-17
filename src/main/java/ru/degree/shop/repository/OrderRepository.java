package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.degree.shop.model.Order;
import ru.degree.shop.model.Product;
import ru.degree.shop.model.ProductVariant;
import ru.degree.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findAllByUser_Id(Long userId);

    Optional<List<Order>> findOrderByIdAndUser_Email(Long id, String userEmail);

    Optional<List<Order>> findAllByUser_EmailOrderByCreatedAtDesc(String userEmail);
//
//    @Query("SELECT CASE WHEN COUNT(oi) > 0 THEN true ELSE false END " +
//            "FROM Order o JOIN o.orderItems oi " +
//            "WHERE o.user = :user AND oi.productVariant = :product")
//    boolean existsByUserAndProduct(@Param("user") User user, @Param("product") Product product);

    boolean existsByUserAndOrderItemsProductVariantProductId(User user, Long productId);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END " +
            "FROM Order o JOIN o.orderItems oi " +
            "WHERE o.user = :user AND oi.productVariant.product.id = :productId")
    boolean existsByUserAndProductId(@Param("user") User user, @Param("productId") Long productId);

}
