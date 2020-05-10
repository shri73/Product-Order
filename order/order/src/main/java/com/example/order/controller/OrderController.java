package com.example.order.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.helper.OrderHelper;
import com.example.order.model.OrderDetails;
import com.example.order.model.Product;
import com.example.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
	
	@Autowired
    private OrderService orderService;
	
	@PostMapping(value = "/add")
    public ResponseEntity<OrderDetails> saveOrder(@RequestBody OrderDetails order){
		log.info("In order");
		OrderDetails addOrder = new OrderDetails();
        addOrder.setItems(order.getItems());
        addOrder.setTotal(OrderHelper.countTotalPrice(order.getItems()));
        addOrder.setOrderedDate(LocalDate.now());
        addOrder.setStatus("PAYMENT_EXPECTED");
        orderService.saveOrder(addOrder);
            
       
        return new ResponseEntity<OrderDetails>(addOrder, HttpStatus.CREATED);
    }
	
	@PostMapping(value = "/add/product", params = {"productId", "quantity"})
    public ResponseEntity addProductToOrder(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("orderId") Long orderId
            ) {
       orderService.addProductToOrder(orderId, productId, quantity);
        return new ResponseEntity(HttpStatus.CREATED);
    }
	
	@GetMapping (value = "/products/{orderId}")
	public ResponseEntity<List<Product>> getAllProductsInOrder(@PathVariable("orderId") Long orderId){
		List<Product> products = orderService.getAllProductsFromOrder(orderId);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		
	}


}
