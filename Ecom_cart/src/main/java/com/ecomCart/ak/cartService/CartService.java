package com.ecomCart.ak.cartService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecomCart.ak.CartEntity.Cart;
import com.ecomCart.ak.CartEntity.Product;
import com.ecomCart.ak.cartRepository.CartRepository;
import com.ecomCart.ak.config.ProductServiceClient;



@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    
    
    @Autowired
    private ProductServiceClient productServiceClient;

    @Value("${product-service.url}")
    private String productServiceUrl;
    

    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
    
    
//    public Product getProductDetails(Long productId) {
//        return productServiceClient.getProductDetails(productId);
//    }
//    
    public Product getProductDetails(Long productId) {
        Optional<Cart> cart = cartRepository.findById(productId);
        if (!cart.isPresent()) {
            throw new RuntimeException("Invalid product ID in cart: " + productId);
        }
        return productServiceClient.getProductDetails(productId);
    }

}
