package com.ardic.stockexchangeapp.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class StockNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Stock not found with parameter: ";


    public StockNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }


    public StockNotFoundException(String name) {
        super(DEFAULT_MESSAGE + name);
    }
}
