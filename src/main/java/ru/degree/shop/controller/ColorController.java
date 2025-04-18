package ru.degree.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.degree.shop.service.ColorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/color")
@AllArgsConstructor
public class ColorController {
    private final ColorService colorService;

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getColor() {
        return new ResponseEntity<>(colorService.getColors(), HttpStatus.OK);
    }
}
