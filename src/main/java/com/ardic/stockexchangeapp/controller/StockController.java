package com.ardic.stockexchangeapp.controller;

import com.ardic.stockexchangeapp.model.dto.CreateStockDTO;
import com.ardic.stockexchangeapp.model.dto.UpdateStockPriceDTO;
import com.ardic.stockexchangeapp.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class StockController {
    private final StockService stockService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createStock(@RequestBody CreateStockDTO createStockDTO) {
        stockService.createStock(createStockDTO);
        return ResponseEntity.ok("Stock added.");
    }

    @PutMapping
    public ResponseEntity<String> updateCurrentPrice(@RequestBody UpdateStockPriceDTO updateStockPriceDTO) {
        stockService.updateStockPrice(updateStockPriceDTO.getId(), updateStockPriceDTO.getUpdatedPrice());
        return ResponseEntity.ok("Stock price updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStockByName(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok("Stock removed.");
    }


}
