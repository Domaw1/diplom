package ru.degree.shop.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.degree.shop.DTO.review.ReviewGetDto;
import ru.degree.shop.DTO.review.ReviewPostDto;
import ru.degree.shop.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("api/v1/reviews")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ReviewGetDto>> getReviewByProductId(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.getReviewsByProductId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReviewGetDto> createReview(@RequestBody @Valid ReviewPostDto reviewPostDto,
                                                     Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(reviewService.addReview(reviewPostDto, email), HttpStatus.CREATED);
    }

    @GetMapping("/hasUserPurchased/{productId}")
    public ResponseEntity<Boolean> getUserPurchased(@PathVariable Long productId
            , Authentication authentication) {
        String email = authentication.getName();

        return new ResponseEntity<>(reviewService.hasUserPurchased(productId, email), HttpStatus.OK);
    }
}
