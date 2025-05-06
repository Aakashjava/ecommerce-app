package com.ecomOrder.ak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecomOrder.ak.entity.Order;
import com.ecomOrder.ak.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${payment-service.url}")
    private String paymentServiceUrl;

    
    
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
    
    public boolean processPayment(Long orderId) {
        String url = paymentServiceUrl + "/payments/process?orderId=" + orderId;
        return restTemplate.postForObject(url, null, Boolean.class);
    }
}