package ru.degree.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.degree.shop.DTO.product.ProductGetDto;
import ru.degree.shop.model.Brand;
import ru.degree.shop.model.Product;
import ru.degree.shop.model.SubCategory;
import ru.degree.shop.repository.BrandRepository;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class, ProductVariantMapper.class})
public interface ProductMapper {

    @Mapping(target = "brand", source = "brand", qualifiedByName = "mapBrand")
    @Mapping(target = "subCategory", source = "subCategory", qualifiedByName = "mapSubCategory")
    @Mapping(target = "rating", source = "rating", qualifiedByName = "mapRating")
    ProductGetDto productToProductGetDto(Product product);

    List<ProductGetDto> productToProductGetDtoList(List<Product> products);

    @Named("mapBrand")
    default String mapBrand(Brand brand) {
        return brand.getName();
    }

    @Named("mapSubCategory")
    default String mapSubCategory(SubCategory subCategory) {
        return subCategory.getName();
    }

    @Named("mapRating")
    default Double mapRating(Double rating) {
        return rating;
    }
}
