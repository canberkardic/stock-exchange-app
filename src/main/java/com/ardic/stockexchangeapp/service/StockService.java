package com.ardic.stockexchangeapp.service;

import com.ardic.stockexchangeapp.model.Stock;
import com.ardic.stockexchangeapp.model.dto.CreateStockDTO;

import java.math.BigDecimal;

public interface StockService {
    void deleteStock(Long id);

    void updateStockPrice(Long id, BigDecimal price);

    Stock createStock(CreateStockDTO stockDTO);

}
