package ru.degree.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.degree.shop.DTO.product.ProductVariantGetDto;
import ru.degree.shop.service.ProductVariantService;

import java.util.List;

@RestController
@RequestMapping("api/v1/variant")
@AllArgsConstructor
public class ProductVariantController {
    private final ProductVariantService service;

    @GetMapping
    public ResponseEntity<List<ProductVariantGetDto>> getProductVariantAll() {
        return new ResponseEntity<>(service.getAllProductVariants(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVariantGetDto> getProductVariantById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getProductVariant(id), HttpStatus.OK);
    }
}
