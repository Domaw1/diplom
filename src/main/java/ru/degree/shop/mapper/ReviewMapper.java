package ru.degree.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.degree.shop.DTO.review.ReviewGetDto;
import ru.degree.shop.model.Product;
import ru.degree.shop.model.Review;
import ru.degree.shop.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "user", source = "user", qualifiedByName = "mapUser")
    @Mapping(target = "product", source = "product", qualifiedByName = "mapProduct")
    ReviewGetDto toReviewGetDto(Review review);

    List<ReviewGetDto> toReviewGetDtoList(List<Review> reviews);

    @Named("mapUser")
    default String mapUser(User user) {
        return user.getUsername();
    }

    @Named("mapProduct")
    default Long mapProduct(Product product) {
        return product.getId();
    }
}
