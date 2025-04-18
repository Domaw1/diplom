package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.ProductColor;

public interface ColorRepository extends JpaRepository<ProductColor, Long> {
}
