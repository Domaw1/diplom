package ru.degree.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.degree.shop.DTO.categories.SubCategoryGetDto;
import ru.degree.shop.model.Category;
import ru.degree.shop.model.SubCategory;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface SubCategoryMapper {
    @Mapping(target = "category", source = "category", qualifiedByName = "mapCategory")
    SubCategoryGetDto toSubCategoryGetDto(SubCategory subCategory);

    List<SubCategoryGetDto> subCategoriesToSubCategoryGetList(List<SubCategory> subCategories);

    @Named("mapCategory")
    default String mapCategory(Category category) {
        return category.getName();
    }
}
