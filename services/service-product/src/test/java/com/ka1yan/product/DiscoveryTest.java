package com.ka1yan.product;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Resource
    NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void nacosServiceDiscovery() throws Exception {
        nacosServiceDiscovery.getServices().forEach(item -> {
            System.out.println("service:" + item);
            try {
                nacosServiceDiscovery.getInstances(item).forEach(instance -> {
                    System.out.println("instance:" + instance.getUri());
                });
            } catch (NacosException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void discoveryClientTest() {
        discoveryClient.getServices().forEach(item -> {
            System.out.println("service:" + item);
            discoveryClient.getInstances(item).forEach(instance -> {
//                System.out.println("service-instance:" + instance.getHost() + ":" + instance.getPort());
                System.out.println(instance.getUri());
                System.out.println(instance.getMetadata());
            });
        });
    }
}
