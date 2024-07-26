package com.ardic.stockexchangeapp.controller;

import com.ardic.stockexchangeapp.model.StockExchange;
import com.ardic.stockexchangeapp.service.StockExchangeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock-exchange")
public class StockExchangeController {
    private final StockExchangeService stockExchangeService;

    @GetMapping(value = "/{name}")
    public ResponseEntity<StockExchange> stockExchangeStocks(@PathVariable("name")
                                                              String stockExchangeName) {
        StockExchange stockExchange = stockExchangeService.listStocksByExchangeName(stockExchangeName);
        return new ResponseEntity<>(stockExchange, HttpStatus.OK);
    }
    @PostMapping(value = "/{name}")
    public ResponseEntity<String> addStockToExchange(@PathVariable("name") String stockExchangeName,
                                                   @RequestParam("stockName") String stockName) {
        stockExchangeService.addStockToExchange(stockExchangeName, stockName);
        return ResponseEntity.ok("Stock added to stock exchange.");
    }
    @DeleteMapping(value = "/{name}")
    public ResponseEntity<String> deleteStockFromExchange(@PathVariable("name") String stockExchangeName,
                                                        @RequestParam("stockName")  String stockName) {
        stockExchangeService.deleteStockFromExchange(stockExchangeName, stockName);
        return ResponseEntity.ok("Stock exchange removed.");
    }





}
