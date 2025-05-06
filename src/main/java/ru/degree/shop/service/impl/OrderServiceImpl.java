package ru.degree.shop.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.order.OrderGetDto;
import ru.degree.shop.DTO.order.OrderStatusUpdateDto;
import ru.degree.shop.exception.EmptyCartException;
import ru.degree.shop.exception.NotFoundException;
import ru.degree.shop.mapper.OrderMapper;
import ru.degree.shop.model.*;
import ru.degree.shop.repository.CartItemRepository;
import ru.degree.shop.repository.CartRepository;
import ru.degree.shop.repository.OrderRepository;
import ru.degree.shop.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderGetDto> getUserOrders(String email) {
        return orderMapper.ordersToOrderGetDtoList(orderRepository.findAllByUser_EmailOrderByCreatedAtDesc(email)
                .orElseThrow(() -> new NotFoundException("User orders not found")));
    }

    @Override
    public List<OrderGetDto> getAllOrders() {
        return orderMapper.ordersToOrderGetDtoList(orderRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    @Override
    public OrderGetDto getUserOrderById(Long orderId) {
        return orderMapper.orderToOrderGetDto(orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("User order not found")));
    }

    @Override
    @Transactional
    public OrderGetDto makeOrder(String email) {
        Cart cart = cartRepository.findCartByUser_Email(email)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        if(cart.getCartItems().isEmpty()) {
            throw new EmptyCartException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus(OrderStatus.NEW);

        BigDecimal total = BigDecimal.ZERO;

        for(CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProductVariant(cartItem.getProductVariant());
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);

            total = total.add(cartItem.getProductVariant().getProduct().getPrice()
                    .multiply(new BigDecimal(cartItem.getQuantity())));

            orderItem.getProductVariant().setQuantity(orderItem.getProductVariant().getQuantity() - cartItem.getQuantity());
        }

        orderRepository.save(order);
        List<CartItem> itemsToDelete = new ArrayList<>(cart.getCartItems());

        cart.getCartItems().clear();
        cartRepository.save(cart);

        cartItemRepository.deleteAll(itemsToDelete);
        return orderMapper.orderToOrderGetDto(order);
    }

    @Override
    public OrderGetDto changeOrderStatus(OrderStatusUpdateDto orderStatusDto) {
        Order order = orderRepository.findById(orderStatusDto.getOrderId())
                .orElseThrow(() -> new NotFoundException("Order not found"));

        order.setOrderStatus(OrderStatus.valueOf(orderStatusDto.getOrderStatus()));
        orderRepository.save(order);
        return orderMapper.orderToOrderGetDto(order);
    }
}
