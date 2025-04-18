package ru.degree.shop.DTO.review;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ReviewGetDto {
    private String user;
    private Long product;
    private String comment;
    private Integer rating;
    private OffsetDateTime createdAt;
}
