package com.ardic.stockexchangeapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private BigDecimal currentPrice;
    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false, updatable = false)
    private Timestamp lastUpdate;

    @ManyToMany(mappedBy = "stocks")
    @JsonIgnore
    private Set<StockExchange> stockExchanges = new HashSet<>();

    public void addToStockExchange(StockExchange stockExchange) {
        this.stockExchanges.add(stockExchange);
        stockExchange.getStocks().add(this);
    }

    public void removeFromStockExchange(StockExchange stockExchange) {
        this.stockExchanges.remove(stockExchange);
        stockExchange.getStocks().remove(this);
    }

    @PreRemove
    private void removeStocksFromExchanges() {
        for (StockExchange stockExchange : stockExchanges) {
            stockExchange.getStocks().remove(this);
        }
    }

}
