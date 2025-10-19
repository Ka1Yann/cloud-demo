package com.ka1yan.order.service;

import com.ka1yan.order.entity.Order;

public interface OrderService {

    Order createOrder(Long userId,Long productId);
}
