package ru.degree.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.degree.shop.DTO.order.OrderGetDto;
import ru.degree.shop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@AllArgsConstructor
    public class OrderController {
    private final OrderService orderService;

    @GetMapping("/user")
    public ResponseEntity<List<OrderGetDto>> getUserOrders(Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(orderService.getUserOrders(email), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<OrderGetDto> getUserOrder(@PathVariable Long id, Authentication authentication) {
        return new ResponseEntity<>(orderService.getUserOrder(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderGetDto> createOrder(Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(orderService.makeOrder(email), HttpStatus.CREATED);
    }
}
