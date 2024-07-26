package com.ardic.stockexchangeapp.exception;

public class StockAlreadyExistsException extends RuntimeException {
    public StockAlreadyExistsException(String stockName, String stockExchangeName) {
        super(String.format("Stock '%s' already exists in Stock Exchange '%s'.", stockName, stockExchangeName));
    }
}
