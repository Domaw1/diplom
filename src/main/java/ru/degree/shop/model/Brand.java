package ru.degree.shop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "brand")
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String logoUrl;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Product> products;
}
