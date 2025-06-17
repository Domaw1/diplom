package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.degree.shop.model.Product;
import ru.degree.shop.model.Review;
import ru.degree.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<List<Review>> findReviewByProduct_Id(Long productId);
    Optional<Review> findByUserAndProduct(User user, Product product);
}
