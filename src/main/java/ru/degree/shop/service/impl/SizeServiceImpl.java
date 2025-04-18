package ru.degree.shop.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.categories.SizeGetDto;
import ru.degree.shop.repository.SizeRepository;
import ru.degree.shop.service.SizeService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    @Override
    public Set<SizeGetDto> getAll() {
        return sizeRepository.findAll().stream()
                .map(size -> {
                    SizeGetDto dto = new SizeGetDto();
                    dto.setSize(size.getSize());
                    return dto;
                })
                .collect(Collectors.toSet());
    }
}
