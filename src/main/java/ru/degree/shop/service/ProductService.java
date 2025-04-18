package ru.degree.shop.service;

import ru.degree.shop.DTO.product.ProductGetDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductGetDto> getAllProducts();

    ProductGetDto getProductById(Long id);

    List<ProductGetDto> filteredProducts
            (List<String> brand, List<String> color, List<String> size,String subCategory, BigDecimal minPrice, BigDecimal maxPrice, String sortBy, String sortDirection);

    String deleteProductById(Long id);

    ProductGetDto createProduct(ProductGetDto product);
}
