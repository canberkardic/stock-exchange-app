package com.ardic.stockexchangeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    @Column(name = "live_in_market")
    private Boolean liveInMarket;


    @ManyToMany
    @JoinTable(name = "stock_exchange_stock",
            joinColumns = {@JoinColumn(name = "stock_exchange_id")}, inverseJoinColumns = {@JoinColumn(name = "stock_id")})
    @JsonIgnore
    private Set<Stock> stocks = new HashSet<>();

}
