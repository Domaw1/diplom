package ru.degree.shop.service;

import ru.degree.shop.DTO.order.OrderGetDto;

import java.util.List;

public interface OrderService {
    List<OrderGetDto> getUserOrders(String email);
    OrderGetDto getUserOrder(Long orderId);
    OrderGetDto makeOrder(String email);
}
