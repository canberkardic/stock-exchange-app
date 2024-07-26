package com.ardic.stockexchangeapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateStockPriceDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "updatedPrice")
    private BigDecimal updatedPrice;
}
