package com.test.exceptions;

public class ResourceNotFoundException extends RuntimeException {
  
	public ResourceNotFoundException(Long id) {
		super("user not present with id=" +  id);
	}
}
