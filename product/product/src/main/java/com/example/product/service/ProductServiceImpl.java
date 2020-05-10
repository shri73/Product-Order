package com.example.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository repository;

	@Override
	public Product save(Product product) {
		return repository.save(product);
	}

	@Override
	public Optional<Product> findById(Long id) {
		Optional<Product> getProductOptional = repository.findById(id);
		return Optional.ofNullable(getProductOptional.get());
	}

	@Override
	public List<Product> findAllProductsByName(String productName) {
		Iterable<Product> productsOptional = repository.findAll();
		return StreamSupport.stream(productsOptional.spliterator(), false).filter(product -> product.getName().equalsIgnoreCase(productName)).collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<Product> findAllProductsByQuantity(Integer quantity) {
		Iterable<Product> productsOptional = repository.findAll();
		return StreamSupport.stream(productsOptional.spliterator(), false).filter(product -> product.getQuantity().equals(quantity)).collect(Collectors.toList());
	}

	@Override
	public void updateQuantityOfProduct(Long id, int substractQuantity) {
		Optional<Product> getProductOptional = repository.findById(id);
		if(getProductOptional.isPresent()) {
			Product updateProduct = getProductOptional.get();
			updateProduct.setQuantity(updateProduct.getQuantity() - substractQuantity);
			repository.save(updateProduct);
		}
		
	}

}
