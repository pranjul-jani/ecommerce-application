package com.friday.productservice.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String exception) {
        super(exception);
    }
}
