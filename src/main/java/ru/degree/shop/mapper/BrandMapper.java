package ru.degree.shop.mapper;

import org.mapstruct.Mapper;
import ru.degree.shop.DTO.categories.BrandGetDto;
import ru.degree.shop.model.Brand;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandGetDto toBrandGetDto(Brand brand);
    List<BrandGetDto> toBrandGetDtoList(List<Brand> brands);
}
