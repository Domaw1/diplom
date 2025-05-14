package ru.degree.shop.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.categories.BrandGetDto;
import ru.degree.shop.exception.NotFoundException;
import ru.degree.shop.mapper.BrandMapper;
import ru.degree.shop.model.Brand;
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

    @Override
    public BrandGetDto createBrand(BrandGetDto brand) {
        Brand newBrand = new Brand();
        newBrand.setName(brand.getName());
        newBrand.setLogoUrl(brand.getLogoUrl());
        Brand savedBrand = brandRepository.save(newBrand);
        return brandMapper.toBrandGetDto(savedBrand);
    }

    @Override
    public BrandGetDto updateBrand(BrandGetDto brand) {
        Brand brandToUpdate = brandRepository.findById(brand.getId())
                .orElseThrow(() -> new NotFoundException("Бренд не найден"));
        brandToUpdate.setName(brand.getName());
        brandToUpdate.setLogoUrl(brand.getLogoUrl());
        Brand savedBrand = brandRepository.save(brandToUpdate);
        return brandMapper.toBrandGetDto(savedBrand);
    }

    @Override
    @Transactional
    public String deleteBrand(BrandGetDto brand) {
        Brand brandToDelete = brandRepository.findByName(brand.getName())
                .orElseThrow(() -> new NotFoundException("Бренд не найден"));
        brandRepository.delete(brandToDelete);
        return "Успешно удалено";
    }

}
