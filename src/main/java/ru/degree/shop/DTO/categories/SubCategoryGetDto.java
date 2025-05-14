package ru.degree.shop.DTO.categories;

import lombok.Data;


@Data
public class SubCategoryGetDto {
    private Long id;
    private String name;
    private String category;
}
