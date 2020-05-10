package com.example.order.service;

import java.util.List;

import com.example.order.model.OrderDetails;
import com.example.order.model.Product;

public interface OrderService {
	public Object saveOrder(OrderDetails order);
	public void addProductToOrder(Long orderId, Long productId, Integer quantity);
	public List<Product> getAllProductsFromOrder(Long orderId);
}
