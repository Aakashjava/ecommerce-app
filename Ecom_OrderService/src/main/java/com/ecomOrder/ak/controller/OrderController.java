package com.ecomOrder.ak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomOrder.ak.entity.Order;
import com.ecomOrder.ak.service.OrderService;

//OrderController.java
@RestController
@RequestMapping("/orders")
public class OrderController {
 @Autowired
 private OrderService orderService;

 @PostMapping
 public Order createOrder(@RequestBody Order order) {
     return orderService.createOrder(order);
 }

 @GetMapping("/{userId}")
 public List<Order> getOrders(@PathVariable Long userId) {
     return orderService.getOrdersByUserId(userId);
 }
}