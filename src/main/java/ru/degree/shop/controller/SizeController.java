package ru.degree.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.degree.shop.DTO.categories.SizeGetDto;
import ru.degree.shop.service.SizeService;

import java.util.Set;

@RestController
@RequestMapping("api/v1/size")
@AllArgsConstructor
public class SizeController {
    private final SizeService sizeService;

    @GetMapping
    public ResponseEntity<Set<SizeGetDto>> getAll() {
        return new ResponseEntity<>(sizeService.getAll(), HttpStatus.OK);
    }
}
