package ru.degree.shop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "product_color")
public class ProductColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;
}
