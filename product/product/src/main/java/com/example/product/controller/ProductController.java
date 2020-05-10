package com.example.product.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.product.model.Product;
import com.example.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
    private ProductService productService;
	
	@GetMapping (value = "/products/{id}")
    public Optional<Product> getProductById(@PathVariable ("id") long id){
		return Optional.ofNullable(productService.findById(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
	    }
    

    @GetMapping (value = "/products", params = "name")
    public List<Product> getAllProductsByName(@RequestParam ("name") String name){
        return productService.findAllProductsByName(name);
    }
    
    @GetMapping (value = "/products", params = "quantity")
    public List<Product> getAllProductsByQuantity(@RequestParam ("quantity") Integer quantity){
        return productService.findAllProductsByQuantity(quantity);
    }
    
    @PostMapping (value = "/products/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    	return new ResponseEntity<Product>(productService.save(product), HttpStatus.CREATED);
    	
    }
    
    @PutMapping(value = "/products/updateQuantity/{id}")
    public void updateProduct(@PathVariable ("id") long id, @RequestParam ("quantity") Integer quantity) {
    	productService.updateQuantityOfProduct(id, quantity);
    }
}
