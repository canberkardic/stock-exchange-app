package com.ardic.stockexchangeapp.exception;

public class StockExchangeNotFoundException extends RuntimeException{
    public StockExchangeNotFoundException(String name) {
        super("Stock Exchange not found with name: " + name);
    }
}
