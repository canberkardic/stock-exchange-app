package com.ardic.stockexchangeapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id of the stock", type = "integer", format = "int64", example = "123")
    private Long id;
    @Column(unique = true)
    @Schema(description = "Name of the stock", type = "string", example = "AAPL", required = true)
    private String name;
    @Schema(description = "Description of the stock", type = "string", example = "Apple Inc.", required = true)
    private String description;
    @Column(name = "current_price")
    @Schema(description = "Current price", type = "number", example = "150.00", required = true)
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
