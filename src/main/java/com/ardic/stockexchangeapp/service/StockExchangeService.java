package com.ardic.stockexchangeapp.service;

import com.ardic.stockexchangeapp.model.StockExchange;

public interface StockExchangeService {
    void deleteStockFromExchange(String stockExchangeName, String stockName);
    void addStockToExchange(String stockExchangeName, String stockName);
    StockExchange listStocksByExchangeName(String stockExchangeName);
}
