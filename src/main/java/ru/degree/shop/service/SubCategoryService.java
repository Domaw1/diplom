package ru.degree.shop.service;

import ru.degree.shop.DTO.categories.SubCategoryGetDto;

import java.util.List;

public interface SubCategoryService {
    List<SubCategoryGetDto> getSubCategories();
}
