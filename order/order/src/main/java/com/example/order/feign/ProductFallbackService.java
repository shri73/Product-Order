package com.example.order.feign;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.order.exception.InvalidQuantity;
import com.example.order.model.Product;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class ProductFallbackService implements ProductClient {

	@Override
	public Optional<Product> getProductById(long id) {
		return Optional.empty();
	}

	@Override
	public void updateProduct(long id, Integer quantity){
		log.info("Getting product {} {}", id, quantity);
		
	}

}
