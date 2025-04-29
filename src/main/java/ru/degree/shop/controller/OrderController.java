package ru.degree.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.degree.shop.DTO.order.OrderGetDto;
import ru.degree.shop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
    public class OrderController {
    private final OrderService orderService;

    @GetMapping("/user")
    public ResponseEntity<List<OrderGetDto>> getUserOrders(Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(orderService.getUserOrders(email), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @PostAuthorize("returnObject.body.user == authentication.name")
    public ResponseEntity<OrderGetDto> getUserOrderByOrderId(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getUserOrderById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderGetDto> createOrder(Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(orderService.makeOrder(email), HttpStatus.CREATED);
    }
}
