package ru.degree.shop.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.degree.shop.model.ProductColor;
import ru.degree.shop.repository.ColorRepository;
import ru.degree.shop.service.ColorService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public List<Map<String, String>> getColors() {
        return colorRepository.findAll()
                .stream()
                .map(color -> Map.of("color", color.getColor())).toList();
    }
}
