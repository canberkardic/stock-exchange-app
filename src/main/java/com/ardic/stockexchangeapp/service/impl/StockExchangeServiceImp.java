package com.ardic.stockexchangeapp.service.impl;

import com.ardic.stockexchangeapp.exceptions.StockAlreadyExistsException;
import com.ardic.stockexchangeapp.exceptions.StockExchangeNotFoundException;
import com.ardic.stockexchangeapp.exceptions.StockNotFoundException;
import com.ardic.stockexchangeapp.model.Stock;
import com.ardic.stockexchangeapp.model.StockExchange;
import com.ardic.stockexchangeapp.repository.StockExchangeRepository;
import com.ardic.stockexchangeapp.repository.StockRepository;
import com.ardic.stockexchangeapp.service.StockExchangeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Transactional
@Service
public class StockExchangeServiceImp implements StockExchangeService {

    private final StockRepository stockRepository;
    private final StockExchangeRepository stockExchangeRepository;

    @Autowired
    public StockExchangeServiceImp(StockRepository stockRepository, StockExchangeRepository stockExchangeRepository) {
        this.stockRepository = stockRepository;
        this.stockExchangeRepository = stockExchangeRepository;
    }

    private StockExchange findStockExchangeByName(String name) {
        Optional<StockExchange> stockExchange = stockExchangeRepository.findByName(name);
        if (stockExchange.isEmpty()) {
            throw new StockExchangeNotFoundException(name);
        }
        return stockExchange.get();
    }

    private Stock findStockByName(String name) {
        Optional<Stock> stock = stockRepository.findByName(name);
        if (stock.isEmpty()) {
            throw new StockNotFoundException(name);
        }
        return stock.get();
    }

    private void updateLiveInMarketStatus(StockExchange stockExchange) {
        boolean liveInMarket = stockExchange.getStocks().size() >= 5;
        stockExchange.setLiveInMarket(liveInMarket);
    }

    @Override
    public void deleteStockFromExchange(String stockExchangeName, String stockName) {
        Stock stock = findStockByName(stockName);
        StockExchange stockExchange = findStockExchangeByName(stockExchangeName);

        stock.removeFromStockExchange(stockExchange);
        updateLiveInMarketStatus(stockExchange);
        stockExchangeRepository.save(stockExchange);
    }

    @Override
    public void addStockToExchange(String stockExchangeName, String stockName) {
        Stock stock = findStockByName(stockName);
        StockExchange stockExchange = findStockExchangeByName(stockExchangeName);

        if (stockExchange.getStocks().contains(stock)) {
            throw new StockAlreadyExistsException(stockName, stockExchangeName);
        }

        stock.addToStockExchange(stockExchange);
        updateLiveInMarketStatus(stockExchange);
        stockExchangeRepository.save(stockExchange);
    }

    @Override
    public StockExchange listStocksByExchangeName(String stockExchangeName) {
        return findStockExchangeByName(stockExchangeName);
    }
}
