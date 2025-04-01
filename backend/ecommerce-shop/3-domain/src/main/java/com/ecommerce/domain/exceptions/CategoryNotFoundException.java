package com.ecommerce.domain.exceptions;

public class CategoryNotFoundException  extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
