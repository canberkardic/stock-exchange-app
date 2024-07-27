package com.ardic.stockexchangeapp.repository;

import com.ardic.stockexchangeapp.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional <Stock> findByName(String stockName);

    boolean existsByName(String name);
}
