package ru.degree.shop.DTO.order;

import lombok.Data;

@Data
public class OrderStatusUpdateDto {
    private Long orderId;
    private String orderStatus;
}
