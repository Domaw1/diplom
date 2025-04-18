package ru.degree.shop.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.categories.SubCategoryGetDto;
import ru.degree.shop.mapper.SubCategoryMapper;
import ru.degree.shop.repository.SubCategoryRepository;
import ru.degree.shop.service.SubCategoryService;

import java.util.List;

@Service
@AllArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final SubCategoryMapper subCategoryMapper;

    @Override
    public List<SubCategoryGetDto> getSubCategories() {
        return subCategoryMapper.subCategoriesToSubCategoryGetList(subCategoryRepository.findAll());
    }
}
