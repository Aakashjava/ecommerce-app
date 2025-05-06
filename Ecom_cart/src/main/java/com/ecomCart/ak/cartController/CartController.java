package com.ecomCart.ak.cartController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomCart.ak.CartEntity.Cart;
import com.ecomCart.ak.CartEntity.Product;
import com.ecomCart.ak.cartService.CartService;

//CartController.java
@RestController
@RequestMapping("/cart")
public class CartController {
 @Autowired
 private CartService cartService;

 @PostMapping
 public Cart addToCart(@RequestBody Cart cart) {
     return cartService.addToCart(cart);
 }

 @GetMapping("/{userId}")
 public List<Cart> getCart(@PathVariable Long userId) {
     return cartService.getCartByUserId(userId);
 }
 
 @GetMapping("/product/{productId}")
 public Product getProductDetails(@PathVariable Long productId) {
	 System.out.println("Fetching details for product ID: " + productId);
     return cartService.getProductDetails(productId);
 }
 
}