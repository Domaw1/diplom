package ru.degree.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.degree.shop.DTO.categories.BrandGetDto;
import ru.degree.shop.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("api/v1/brand")
@AllArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandGetDto>> getAllBrands() {
        return new ResponseEntity<>(brandService.getAllBrands(), HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BrandGetDto> addBrand(@RequestBody BrandGetDto brand) {
        return new ResponseEntity<>(brandService.createBrand(brand), HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BrandGetDto> updateBrand(@RequestBody BrandGetDto brand) {
        return new ResponseEntity<>(brandService.updateBrand(brand), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteBrand(@RequestBody BrandGetDto brand) {
        return new ResponseEntity<>(brandService.deleteBrand(brand), HttpStatus.OK);
    }
}
