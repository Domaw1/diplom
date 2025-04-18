package ru.degree.shop.service;

import ru.degree.shop.DTO.product.ProductVariantGetDto;

import java.util.List;

public interface ProductVariantService {
    List<ProductVariantGetDto> getAllProductVariants();
    ProductVariantGetDto getProductVariant(Long id);
    ProductVariantGetDto addProductVariant(ProductVariantGetDto productVariant);
    String deleteProductVariant(Long id);
}
