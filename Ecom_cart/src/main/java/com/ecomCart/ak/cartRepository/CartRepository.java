package com.ecomCart.ak.cartRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomCart.ak.CartEntity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);
}