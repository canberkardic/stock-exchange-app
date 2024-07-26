package com.ardic.stockexchangeapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateStockPriceDTO {

    @JsonProperty(value = "id")
    @Schema(description = "id of the stock", type = "integer", format = "int64", example = "123")
    private Long id;

    @JsonProperty(value = "updatedPrice")
    @Schema(description = "The price requested to update the stock", type = "number", example = "175.00", required = true)
    private BigDecimal updatedPrice;
}
