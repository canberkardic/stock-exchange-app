package com.ardic.stockexchangeapp.repository;

import com.ardic.stockexchangeapp.model.Stock;
import com.ardic.stockexchangeapp.model.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {
    Optional<StockExchange> findByName(String exchangeName);

}
