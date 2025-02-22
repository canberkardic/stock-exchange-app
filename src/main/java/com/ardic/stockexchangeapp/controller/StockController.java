package com.ardic.stockexchangeapp.controller;

import com.ardic.stockexchangeapp.model.dto.CreateStockDTO;
import com.ardic.stockexchangeapp.model.dto.UpdateStockPriceDTO;
import com.ardic.stockexchangeapp.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class StockController {
    private final StockService stockService;


    @Operation(summary = "Create a stock")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createStock(@RequestBody CreateStockDTO createStockDTO) {
        stockService.createStock(createStockDTO);
        String responseMessage = String.format("'%s' Stock created", createStockDTO.getName());
        return ResponseEntity.ok(responseMessage );
    }


    @Operation(summary = "Update the price of a stock")
    @PutMapping
    public ResponseEntity<String> updateCurrentPrice(@RequestBody UpdateStockPriceDTO updateStockPriceDTO) {
        stockService.updateStockPrice(updateStockPriceDTO.getId(), updateStockPriceDTO.getUpdatedPrice());
        String responseMessage = String.format("Stock price updated to '%s'", updateStockPriceDTO.getUpdatedPrice());
        return ResponseEntity.ok(responseMessage);
    }


    @Operation(summary = "Deleting a stock from the system")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStockByName(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok("Stock removed.");
    }


}
