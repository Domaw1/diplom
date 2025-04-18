package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {
}
