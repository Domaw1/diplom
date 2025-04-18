package ru.degree.shop.DTO.order;

import lombok.Data;
import ru.degree.shop.model.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class OrderGetDto {
    private Long id;
    private Long user;
    private BigDecimal total;
    private OrderStatus orderStatus;
    private OffsetDateTime createdAt;
    private List<OrderItemGetDto> orderItems;
}
