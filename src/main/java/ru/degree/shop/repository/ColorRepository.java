package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.ProductColor;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<ProductColor, Long> {
    Optional<ProductColor> findByColor(String color);
}
