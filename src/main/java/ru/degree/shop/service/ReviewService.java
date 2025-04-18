package ru.degree.shop.service;

import ru.degree.shop.DTO.review.ReviewGetDto;
import ru.degree.shop.DTO.review.ReviewPostDto;

import java.util.List;

public interface ReviewService {
    List<ReviewGetDto> getReviewsByProductId(Long productId);
    ReviewGetDto addReview(ReviewPostDto reviewGetDto, String email);
}
