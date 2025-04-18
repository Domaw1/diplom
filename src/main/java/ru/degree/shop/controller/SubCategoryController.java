package ru.degree.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
