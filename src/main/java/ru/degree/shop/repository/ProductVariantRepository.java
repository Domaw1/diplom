package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
}
