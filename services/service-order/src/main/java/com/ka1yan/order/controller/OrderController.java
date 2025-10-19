package com.ka1yan.order.controller;

import com.ka1yan.order.entity.Order;
import com.ka1yan.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/create")
    public Order createOrder(@RequestParam Long userId,
                             @RequestParam Long productId) {
        return orderService.createOrder(userId, productId);
    }
}
