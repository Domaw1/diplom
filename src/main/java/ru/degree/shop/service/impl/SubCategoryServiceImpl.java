package ru.degree.shop.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.categories.SubCategoryGetDto;
import ru.degree.shop.exception.NotFoundException;
import ru.degree.shop.mapper.SubCategoryMapper;
import ru.degree.shop.model.Brand;
import ru.degree.shop.model.Category;
import ru.degree.shop.model.SubCategory;
import ru.degree.shop.repository.CategoryRepository;
import ru.degree.shop.repository.SubCategoryRepository;
import ru.degree.shop.service.SubCategoryService;

import java.util.List;

@Service
@AllArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final SubCategoryMapper subCategoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<SubCategoryGetDto> getSubCategories() {
        return subCategoryMapper.subCategoriesToSubCategoryGetList(subCategoryRepository.findAll());
    }

    @Override
    public SubCategoryGetDto createSubCategory(SubCategoryGetDto subCategory) {
        SubCategory newCategory = new SubCategory();
        newCategory.setName(subCategory.getName());
        Category category = categoryRepository.findById(1L)
                        .orElseThrow(() -> new NotFoundException("Category not found"));
        newCategory.setCategory(category);
        SubCategory savedCategory = subCategoryRepository.save(newCategory);
        return subCategoryMapper.toSubCategoryGetDto(savedCategory);
    }

    @Override
    public SubCategoryGetDto updateSubCategory(SubCategoryGetDto subCategory) {
        SubCategory subCategoryToUpdate = subCategoryRepository.findById(subCategory.getId())
                .orElseThrow(() -> new NotFoundException("SubCategory not found"));

        subCategoryToUpdate.setName(subCategory.getName());
        SubCategory savedCategory = subCategoryRepository.save(subCategoryToUpdate);
        return subCategoryMapper.toSubCategoryGetDto(savedCategory);
    }

    @Override
    public String deleteSubCategory(SubCategoryGetDto subCategory) {
        subCategoryRepository.deleteById(subCategory.getId());
        return "Категория удалена";
    }
}
