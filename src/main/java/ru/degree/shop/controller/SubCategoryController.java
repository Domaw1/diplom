package ru.degree.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.degree.shop.DTO.categories.SubCategoryGetDto;
import ru.degree.shop.service.SubCategoryService;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@AllArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<List<SubCategoryGetDto>> getAllSubCategories() {
        return new ResponseEntity<>(subCategoryService.getSubCategories(), HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SubCategoryGetDto> createSubCategory(@RequestBody SubCategoryGetDto subCategoryGetDto) {
        return new ResponseEntity<>(subCategoryService.createSubCategory(subCategoryGetDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteSubCategory(@RequestBody SubCategoryGetDto subCategoryGetDto) {
        return new ResponseEntity<>(subCategoryService.deleteSubCategory(subCategoryGetDto), HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SubCategoryGetDto> updateSubCategory(@RequestBody SubCategoryGetDto subCategoryGetDto) {
        return new ResponseEntity<>(subCategoryService.updateSubCategory(subCategoryGetDto), HttpStatus.OK);
    }
}
