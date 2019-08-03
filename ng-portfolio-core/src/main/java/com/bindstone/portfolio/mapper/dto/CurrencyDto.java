package com.bindstone.portfolio.mapper.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyDto {

    private Long id;
    private String iso;
    private String name;
    private BigDecimal exchange;

}
