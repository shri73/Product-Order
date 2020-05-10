package com.example.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.order.model.OrderDetails;
@Repository
public interface OrderRepository extends CrudRepository<OrderDetails, Long> {

}
