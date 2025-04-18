package ru.degree.shop.DTO.token;

import lombok.Data;

@Data
public class ReceiptPostDto {
    private Long orderId;
    private String receiptImage;
}
