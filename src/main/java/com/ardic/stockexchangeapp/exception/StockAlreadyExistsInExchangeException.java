package com.ardic.stockexchangeapp.exception;

public class StockAlreadyExistsInExchangeException extends RuntimeException {
    public StockAlreadyExistsInExchangeException(String stockName, String stockExchangeName) {
        super(String.format("Stock '%s' already exists in Stock Exchange '%s'.", stockName, stockExchangeName));
    }
}
