package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.ProductVariant;

import java.util.Optional;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    Optional<ProductVariant> deleteAllByProduct_Id(Long productId);
}
