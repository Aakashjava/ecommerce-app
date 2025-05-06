package com.ecomPayment.ak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomPayment.ak.service.PaymentService;

//PaymentController.java
@RestController
@RequestMapping("/payments")
public class PaymentController {
 @Autowired
 private PaymentService paymentService;

 
 @PostMapping("/process")
 public boolean processPayment(@RequestParam Long orderId) {
     return paymentService.processPayment(orderId);
 }
 
 
 
}