package com.ecomPayment.ak.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomPayment.ak.enity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}