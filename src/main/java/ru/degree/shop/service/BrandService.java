package ru.degree.shop.service;

import ru.degree.shop.DTO.categories.BrandGetDto;

import java.util.List;

public interface BrandService {
    List<BrandGetDto> getAllBrands();
}
