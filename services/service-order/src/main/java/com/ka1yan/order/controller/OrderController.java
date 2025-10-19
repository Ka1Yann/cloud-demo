package com.ka1yan.order.controller;

import com.ka1yan.order.entity.Order;
import com.ka1yan.order.properties.OrderProperties;
import com.ka1yan.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderProperties orderProperties;
//    @Value("${order.timeout}")
//    private Integer orderTimeout;
//    @Value("${order.auto-confirm}")
//    private Integer autoConfirm;



    @GetMapping("/prop")
    public String  getProperties() {
        return "order.timeout" + orderProperties.getTimeout() + "; auto-confirm" + orderProperties.getAutoConfirm();
//        return "order.timeout:" + orderTimeout + "; auto-confirm:" + autoConfirm;
    }

    @GetMapping("/create")
    public Order createOrder(@RequestParam Long userId,
                             @RequestParam Long productId) {
        return orderService.createOrder(userId, productId);
    }
}
