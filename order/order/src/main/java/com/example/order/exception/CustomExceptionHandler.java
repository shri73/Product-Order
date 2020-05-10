package com.example.order.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import com.netflix.client.http.HttpHeaders;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleException(NoProductsInOrder ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Bad Request", details);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
	
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	       String error = "Malformed JSON request";
	       List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	       ErrorResponse errorResponse = new ErrorResponse("error", details);
	       return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	   }
	
	@ExceptionHandler(InternalServerError.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @ExceptionHandler(NoProductsInOrder.class)
    public final ResponseEntity<Object> handleUserNotFoundException(NoProductsInOrder ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("No Products Found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
 

}
