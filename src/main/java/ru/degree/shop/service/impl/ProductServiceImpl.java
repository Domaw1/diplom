package ru.degree.shop.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.product.ProductCreateDto;
import ru.degree.shop.DTO.product.ProductGetDto;
import ru.degree.shop.exception.NotFoundException;
import ru.degree.shop.mapper.ProductMapper;
import ru.degree.shop.mapper.ProductVariantMapper;
import ru.degree.shop.model.*;
import ru.degree.shop.repository.*;
import ru.degree.shop.service.ProductService;
import ru.degree.shop.specification.ProductSpecification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BrandRepository brandRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantMapper productVariantMapper;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @Override
    public List<ProductGetDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.productToProductGetDtoList(products);
    }

    @Override
    public ProductGetDto getProductById(Long id) {
        return productMapper.productToProductGetDto(productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
    }

    @Override
    public List<ProductGetDto> filteredProducts
            (List<String> brand, List<String> color, List<String> size, String subCategory, BigDecimal minPrice,
             BigDecimal maxPrice, String sortBy, String sortDirection) {
        Specification<Product> specification = ProductSpecification
                .filterProduct(brand, color, size,subCategory, minPrice, maxPrice);

        Sort sort = Sort.unsorted();

        if(sortBy != null) {
            sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy);
        }
        List<Product> products = productRepository.findAll(specification, sort);

        return productMapper.productToProductGetDtoList(products);
    }

    @Override
    @Transactional
    public String deleteProductById(Long id) {
        productRepository.deleteById(id);
        return "Товар удален";
    }

    @Override
    @Transactional
    public ProductGetDto createProduct(ProductCreateDto product) {
        Brand brand = brandRepository.findByName(product.getBrand())
                .orElseThrow(() -> new NotFoundException("Brand not found"));

        SubCategory subCategory = subCategoryRepository.findByName(product.getSubCategory())
                .orElseThrow(() -> new NotFoundException("SubCategory not found"));

        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        newProduct.setGender(product.getGender());
        newProduct.setBrand(brand);
        newProduct.setSubCategory(subCategory);
        newProduct.setImageUrls(product.getImageUrls());
        Product savedProduct = productRepository.save(newProduct);

        if(product.getProductVariants() != null && !product.getProductVariants().isEmpty()) {
            var variants = product.getProductVariants().stream()
                    .map(variantDto -> {
                        var variant = productVariantMapper.productVariantDtoToProductVariant(variantDto);
                        variant.setProduct(savedProduct);
                        ProductColor color = colorRepository.findByColor(variantDto.getColor())
                                .orElseThrow(() -> new NotFoundException("Color not found: " + variantDto.getColor()));
                        variant.setColor(color);

                        Size size = sizeRepository.findBySize(variantDto.getSize())
                                .orElseThrow(() -> new NotFoundException("Size not found: " + variantDto.getSize()));
                        variant.setSize(size);
                        return variant;
                    }).toList();
            productVariantRepository.saveAll(variants);
            savedProduct.setProductVariants(variants);
        }
        return productMapper.productToProductGetDto(savedProduct);
    }

    @Override
    @Transactional
    public ProductGetDto updateProduct(ProductCreateDto productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Brand brand = brandRepository.findByName(productDto.getBrand())
                .orElseThrow(() -> new NotFoundException("Brand not found"));

        SubCategory subCategory = subCategoryRepository.findByName(productDto.getSubCategory())
                .orElseThrow(() -> new NotFoundException("SubCategory not found"));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setGender(productDto.getGender());
        product.setBrand(brand);
        product.setSubCategory(subCategory);
        product.setImageUrls(productDto.getImageUrls());

        if (productDto.getProductVariants() != null && !productDto.getProductVariants().isEmpty()) {
            var variants = productDto.getProductVariants().stream()
                    .map(variantDto -> {
                        var variant = productVariantMapper.productVariantDtoToProductVariant(variantDto);
                        variant.setProduct(product);

                        ProductColor color = colorRepository.findByColor(variantDto.getColor())
                                .orElseThrow(() -> new NotFoundException("Color not found: " + variantDto.getColor()));
                        variant.setColor(color);

                        Size size = sizeRepository.findBySize(variantDto.getSize())
                                .orElseThrow(() -> new NotFoundException("Size not found: " + variantDto.getSize()));
                        variant.setSize(size);
                        return variant;
                    }).toList();

            if (product.getProductVariants() == null) {
                product.setProductVariants(new ArrayList<>());
            } else {
                product.getProductVariants().clear();
            }
            product.getProductVariants().addAll(variants);
        }

        Product updated = productRepository.save(product);
        return productMapper.productToProductGetDto(updated);
    }

}
