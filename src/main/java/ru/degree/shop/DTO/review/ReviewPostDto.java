package ru.degree.shop.DTO.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewPostDto {
    private Long productId;
    @Size(min = 1, max = 100)
    @NotBlank
    private String comment;
    @Min(1)
    @Max(5)
    private Integer rating;
}
