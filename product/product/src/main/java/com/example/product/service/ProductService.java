package com.example.product.service;

import java.util.List;
import java.util.Optional;

import com.example.product.model.Product;

public interface ProductService {
	Product save(Product product);
	Optional<Product> findById(Long id);
    List<Product> findAllProductsByName(String productName);
    void deleteById(Long id);
    List<Product> findAllProductsByQuantity(Integer quantity);
    void updateQuantityOfProduct(Long id, int updatedQuantity);

}
