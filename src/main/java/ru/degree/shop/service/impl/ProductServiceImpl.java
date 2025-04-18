package ru.degree.shop.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.product.ProductGetDto;
import ru.degree.shop.exception.NotFoundException;
import ru.degree.shop.mapper.ProductMapper;
import ru.degree.shop.model.Brand;
import ru.degree.shop.model.Product;
import ru.degree.shop.model.SubCategory;
import ru.degree.shop.repository.BrandRepository;
import ru.degree.shop.repository.ProductRepository;
import ru.degree.shop.repository.SubCategoryRepository;
import ru.degree.shop.service.ProductService;
import ru.degree.shop.specification.ProductSpecification;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BrandRepository brandRepository;
    private final SubCategoryRepository subCategoryRepository;

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
            (List<String> brand, List<String> color, List<String> size, String subCategory, BigDecimal minPrice, BigDecimal maxPrice, String sortBy, String sortDirection) {
        Specification<Product> specification = ProductSpecification.filterProduct(brand, color, size,subCategory, minPrice, maxPrice);

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
    public ProductGetDto createProduct(ProductGetDto product) {
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
        return productMapper.productToProductGetDto(savedProduct);
    }
}
