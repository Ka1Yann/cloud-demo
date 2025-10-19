package com.ka1yan.order.service.impl;

import com.ka1yan.order.entity.Order;
import com.ka1yan.order.service.OrderService;
import com.ka1yan.product.entity.Product;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Override
    public Order createOrder(Long productId, Long userId) {
        Order order = new Order();
        Product productRemote = getProductRemoteWithLoadBalanceAnnotation(productId);
        if (productRemote == null) {
            return null;
        }
        order.setId(new Random().nextLong(10000, 99999));
        order.setUserId(userId);
        order.setAddress("home");
        order.setNickName("ZhangSan");
        order.setTotalAmount(productRemote.getPrice().multiply(BigDecimal.valueOf(productRemote.getNum())));
        order.setProductList(Collections.singletonList(productRemote));
        return order;
    }

    private Product getProductRemote(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        if (instances == null || instances.isEmpty()) {
            return null;
        }
        ServiceInstance instance = instances.get(0);
        String url = instance.getUri().toString() + "/product/" + productId;
        log.info("===>Received a rpc get-product request:{}", url);
        return restTemplate.getForObject(url, Product.class);
    }

    private Product getProductRemoteWithLoadBalance(Long productId) {
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url = choose.getUri().toString() + "/product/" + productId;
        log.info("===>Received a load-balanced rpc get-product request:{}", url);
        return restTemplate.getForObject(url, Product.class);
    }

    private Product getProductRemoteWithLoadBalanceAnnotation(Long productId) {
        String url = "http://service-product/product/" + productId;
        log.info("===>Received a load-balanced-annotation rpc get-product request:{}", url);
        return restTemplate.getForObject(url, Product.class);
    }
}
