package ru.degree.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.degree.shop.DTO.product.ProductGetDto;
import ru.degree.shop.DTO.product.ProductVariantGetDto;
import ru.degree.shop.model.Product;
import ru.degree.shop.model.ProductColor;
import ru.degree.shop.model.ProductVariant;
import ru.degree.shop.model.Size;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    @Mapping(target = "product", source = "product", qualifiedByName = "mapProduct")
    @Mapping(target = "size", source = "size", qualifiedByName = "mapSize")
    @Mapping(target = "color", source = "color", qualifiedByName = "mapColor")
    ProductVariantGetDto productVariantToProductVariantGetDto(ProductVariant productVariant);

    List<ProductVariantGetDto> productVariantToProductVariantGetDtoList(List<ProductVariant> productVariants);

    @Named("mapProduct")
    default ProductGetDto mapProduct(Product product) {
        ProductGetDto productGetDto = new ProductGetDto();
        productGetDto.setName(product.getName());
        productGetDto.setDescription(product.getDescription());
        productGetDto.setPrice(product.getPrice());
        productGetDto.setImageUrls(product.getImageUrls());
        return productGetDto;
    }

    @Named("mapSize")
    default String mapSize(Size product) {
        return product.getSize();
    }

    @Named("mapColor")
    default String mapColor(ProductColor product) {
        return product.getColor();
    }
}
