package ru.degree.shop.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.review.ReviewGetDto;
import ru.degree.shop.DTO.review.ReviewPostDto;
import ru.degree.shop.exception.NotFoundException;
import ru.degree.shop.mapper.ReviewMapper;
import ru.degree.shop.model.Product;
import ru.degree.shop.model.Review;
import ru.degree.shop.model.User;
import ru.degree.shop.repository.ProductRepository;
import ru.degree.shop.repository.ReviewRepository;
import ru.degree.shop.repository.UserRepository;
import ru.degree.shop.service.ReviewService;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewGetDto> getReviewsByProductId(Long productId) {
        return reviewMapper.toReviewGetDtoList(reviewRepository.findReviewByProduct_Id(productId)
                .orElseThrow(() -> new NotFoundException("Not found")));
    }

    @Override
    @Transactional
    public ReviewGetDto addReview(ReviewPostDto reviewPostDto, String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Product product = productRepository.findById(reviewPostDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setComment(reviewPostDto.getComment());
        review.setRating(reviewPostDto.getRating());

        Review savedReview = reviewRepository.save(review);

        return reviewMapper.toReviewGetDto(savedReview);
    }
}
