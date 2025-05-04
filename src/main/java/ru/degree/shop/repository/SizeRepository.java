package ru.degree.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.degree.shop.model.Size;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size, Long> {
    Optional<Size> findBySize(String size);
}
