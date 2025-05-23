package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
