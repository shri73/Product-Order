package com.example.order.feign;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "product", fallback=ProductFallbackService.class )
public interface ProductClient {
	
	@GetMapping (value = "/products/{id}")
    public Optional<com.example.order.model.Product> getProductById(@PathVariable ("id") long id);
	
	@PutMapping(value = "/products/updateQuantity/{id}")
    public void updateProduct(@PathVariable ("id") long id, @RequestParam ("quantity") Integer quantity);
}
