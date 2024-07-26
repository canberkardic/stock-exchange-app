package com.ardic.stockexchangeapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class CreateStockDTO {

    @JsonProperty("name")
    @Schema(description = "Name of the stock", type = "string", example = "AAPL", required = true)
    private String name;
    @JsonProperty("description")
    @Schema(description = "Description of the stock", type = "string", example = "Apple Inc.", required = true)
    private String description;
    @JsonProperty("price")
    @Schema(description = "Current price", type = "number", example = "150.00", required = true)
    private BigDecimal price;
}
