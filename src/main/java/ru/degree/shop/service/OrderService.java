package ru.degree.shop.service;

import ru.degree.shop.DTO.order.OrderGetDto;
import ru.degree.shop.DTO.order.OrderStatusUpdateDto;

import java.util.List;

public interface OrderService {
    List<OrderGetDto> getUserOrders(String email);
    List<OrderGetDto> getAllOrders();
    OrderGetDto getUserOrderById(Long orderId);
    OrderGetDto makeOrder(String email);
    OrderGetDto changeOrderStatus(OrderStatusUpdateDto orderStatusDto);
}
