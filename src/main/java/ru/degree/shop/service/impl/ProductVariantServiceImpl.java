package ru.degree.shop.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.product.ProductVariantGetDto;
import ru.degree.shop.exception.NotFoundException;
import ru.degree.shop.mapper.ProductVariantMapper;
import ru.degree.shop.model.ProductVariant;
import ru.degree.shop.repository.ProductVariantRepository;
import ru.degree.shop.service.ProductVariantService;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantMapper productVariantMapper;

    @Override
    public List<ProductVariantGetDto> getAllProductVariants() {
        return productVariantMapper
                .productVariantToProductVariantGetDtoList(productVariantRepository.findAll());
    }

    @Override
    public ProductVariantGetDto getProductVariant(Long id) {
        ProductVariant productVariant = productVariantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product variant not found"));

        return productVariantMapper.productVariantToProductVariantGetDto(productVariant);
    }

    @Override
    public ProductVariantGetDto addProductVariant(ProductVariantGetDto productVariant) {

        return null;
    }

    @Override
    public String deleteProductVariant(Long id) {
        productVariantRepository.deleteById(id);
        return "Товар удален";
    }
}
