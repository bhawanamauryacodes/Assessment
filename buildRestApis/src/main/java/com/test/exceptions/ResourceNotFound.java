package com.test.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceNotFound {
    
	@ExceptionHandler(ResourceNotFoundException.class)
public Map<String,String> exceptionHandler(ResourceNotFoundException exception){
		Map<String,String> error = new HashMap<>();
		error.put("message", exception.getMessage());
		return error;	
	}
}
