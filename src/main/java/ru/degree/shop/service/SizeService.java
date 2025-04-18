package ru.degree.shop.service;

import ru.degree.shop.DTO.categories.SizeGetDto;

import java.util.Set;

public interface SizeService {
    Set<SizeGetDto> getAll();
}
