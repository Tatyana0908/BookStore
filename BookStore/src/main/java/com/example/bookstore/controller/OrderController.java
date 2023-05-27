package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/order")
    public List<OrderDto> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        Optional<OrderDto> orderOptional = service.getOrderById(id);
        if (orderOptional.isPresent()) {
            OrderDto order = orderOptional.get();
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            String errorMessage = "Заказ не найден с id " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/order/new")
    public void createOrder(@RequestBody OrderDto orderDto) {
        service.createOrder(orderDto);
    }

}
