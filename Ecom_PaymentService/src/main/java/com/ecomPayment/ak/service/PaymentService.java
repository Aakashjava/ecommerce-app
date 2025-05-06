package com.ecomPayment.ak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomPayment.ak.Repository.PaymentRepository;
import com.ecomPayment.ak.enity.Payment;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public boolean processPayment(Long orderId) {
        // Simulate payment processing
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setStatus(Math.random() > 0.5 ? "SUCCESS" : "FAILED");
        paymentRepository.save(payment);
        return payment.getStatus().equals("SUCCESS");
    }
}


//@Service
//public class PaymentService {
//    public boolean processPayment(Long orderId) {
//        // Simulate payment processing
//        return Math.random() > 0.5; // Randomly return true/false
//    }
//    
//    
//}
