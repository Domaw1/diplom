package ru.degree.shop.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "product_color")
public class ProductColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;
}
