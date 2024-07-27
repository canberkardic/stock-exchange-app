package com.ardic.stockexchangeapp.exception;

public class StockAlreadyExistsException extends RuntimeException {
    public StockAlreadyExistsException(String stockName) {
        super(String.format("Stock '%s' already exists.", stockName));
    }
}
