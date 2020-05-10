package com.example.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoProductsInOrder extends RuntimeException{

	public NoProductsInOrder(String message) {
		super(message);
	}
}
