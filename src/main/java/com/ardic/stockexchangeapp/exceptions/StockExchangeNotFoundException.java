package com.ardic.stockexchangeapp.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class StockExchangeNotFoundException extends EntityNotFoundException{
    public StockExchangeNotFoundException(String name) {
        super("Stock Exchange not found with name: " + name);
    }
}
