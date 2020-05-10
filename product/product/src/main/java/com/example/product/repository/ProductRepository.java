package com.example.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.product.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>  {

}
