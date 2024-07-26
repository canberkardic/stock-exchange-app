package com.ardic.stockexchangeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock_exchange")
public class StockExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id of the stock exchange", type = "integer", format = "int64", example = "123")
    private Long id;
    @Column(unique = true)
    @Schema(description = "Name of the stock exchange", type = "string", example = "NASDAQ", required = true)
    private String name;
    @Schema(description = "Description of the stock exchange", type = "string", example = "NASDAQ Stock Market", required = true)
    private String description;
    @Column(name = "live_in_market")
    @Schema(description = "Defines if stock is live in market (has >=5 stocks) ", type = "boolean", example = "true", required = false)
    private Boolean liveInMarket;

    @ManyToMany
    @JoinTable(name = "stock_exchange_stock",
            joinColumns = {@JoinColumn(name = "stock_exchange_id")}, inverseJoinColumns = {@JoinColumn(name = "stock_id")})
    @JsonIgnore
    private Set<Stock> stocks = new HashSet<>();

}
