package ru.degree.shop.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.categories.BrandGetDto;
import ru.degree.shop.mapper.BrandMapper;
import ru.degree.shop.repository.BrandRepository;
import ru.degree.shop.service.BrandService;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public List<BrandGetDto> getAllBrands() {
        return brandMapper.toBrandGetDtoList(brandRepository.findAll());
    }
}
