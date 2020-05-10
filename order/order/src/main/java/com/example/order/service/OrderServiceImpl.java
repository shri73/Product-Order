package com.example.order.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.order.exception.NoProductsInOrder;
import com.example.order.feign.ProductClient;
import com.example.order.model.OrderDetails;
import com.example.order.model.Product;
import com.example.order.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductClient client;
	
	@Override
	public Object saveOrder( @Valid @NotNull OrderDetails order) {
		boolean checkProductExists = order.getItems().stream().allMatch((eachProduct) ->
		checkIfProductExists(eachProduct));
		boolean checkIfQuantityExists =  order.getItems().stream().allMatch((eachProduct) ->
		checkIfQuantityExists(eachProduct));
		if(checkProductExists && checkIfQuantityExists) {
			
			order.getItems().stream().forEach(x -> client.updateProduct(x.getId(), x.getQuantity()));
			return repository.save(order);
		}
		else {
			throw new NoProductsInOrder("Product Not Found");
		}
		
	}
	
	private boolean checkIfProductExists(Product eachProduct) {
        boolean productExists = false;
        productExists = client.getProductById(eachProduct.getId()).isPresent();
		return productExists;
	}
	
	private boolean checkIfQuantityExists(Product eachProduct) {
		boolean quantityExists = false;
		Optional<Product> getProduct = client.getProductById(eachProduct.getId());
		if(getProduct.isPresent()) {
			if(eachProduct.getQuantity() <= getProduct.get().getQuantity()) {
				quantityExists = true;
				return quantityExists;
			}
		
		}
		return quantityExists;
		
	}
	
	private Product getProduct(Long productId) {
		
       Optional<Product> productOptional = client.getProductById(productId);
       return productOptional.isPresent() ? productOptional.get() : null;
	}

	@Override
	public void addProductToOrder(Long orderId, Long productId, Integer quantity) {
		Optional<Product> product = Optional.ofNullable(getProduct(productId));
		Optional<OrderDetails> orderOptional;
		if(product.isPresent()) {
			Product productToBeAddedProduct = product.get();
			productToBeAddedProduct.setQuantity(quantity);
			orderOptional = repository.findById(orderId);
			if(orderOptional.isPresent()) {
				OrderDetails existingOrder = orderOptional.get();			
				existingOrder.getItems().add(productToBeAddedProduct);
			}
		}
		
	}

	@Override
	public List<Product> getAllProductsFromOrder(Long orderId) {
		return Optional.ofNullable(repository.findById(orderId).get().getItems()).orElseThrow(() -> new NoProductsInOrder("No Products found in Order"));
	}

}
