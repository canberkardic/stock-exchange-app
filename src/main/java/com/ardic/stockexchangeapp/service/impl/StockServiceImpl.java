package com.ardic.stockexchangeapp.service.impl;

import com.ardic.stockexchangeapp.exceptions.StockNotFoundException;
import com.ardic.stockexchangeapp.model.Stock;
import com.ardic.stockexchangeapp.model.StockExchange;
import com.ardic.stockexchangeapp.model.dto.CreateStockDTO;
import com.ardic.stockexchangeapp.repository.StockExchangeRepository;
import com.ardic.stockexchangeapp.repository.StockRepository;
import com.ardic.stockexchangeapp.service.StockService;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Transactional
@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    private final StockExchangeRepository stockExchangeRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, StockExchangeRepository stockExchangeRepository) {
        this.stockRepository = stockRepository;
        this.stockExchangeRepository = stockExchangeRepository;
    }

    private Stock findStockById(Long id) {
        Optional<Stock> stock = stockRepository.findById(id);
        if (stock.isEmpty()) {
            throw new StockNotFoundException(id);
        }
        return stock.get();
    }

    @Override
    public void deleteStock(Long id) {
        Stock stock = findStockById(id);
        for (StockExchange stockExchange : stock.getStockExchanges()) {
            boolean liveInMarket = stockExchange.getStocks().size() > 5;
            stockExchange.setLiveInMarket(liveInMarket);
        }
        stockExchangeRepository.saveAll(stock.getStockExchanges());
        stockRepository.deleteById(id);
    }

    @Override
    public void updateStockPrice(Long id, BigDecimal price) {
        Stock stock = findStockById(id);
        stock.setCurrentPrice(price);
        stockRepository.save(stock);
    }

    @Override
    public Stock createStock(CreateStockDTO stockDTO) {
        Stock stock = new Stock();

        stock.setName(stockDTO.getName());
        stock.setDescription(stockDTO.getName());
        stock.setCurrentPrice(stockDTO.getPrice());

        return stockRepository.save(stock);

    }
}
