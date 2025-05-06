package com.ecomCart.ak.config;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecomCart.ak.CartEntity.Product;

@FeignClient(name = "product-service", url = "${product-service.url}")
public interface ProductServiceClient {

    @GetMapping("/products/{productId}")
    Product getProductDetails(@PathVariable Long productId);
}