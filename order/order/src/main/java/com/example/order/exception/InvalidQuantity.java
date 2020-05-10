package com.example.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidQuantity extends RuntimeException{

	public InvalidQuantity(String message) {
		super(message);
	}

}
