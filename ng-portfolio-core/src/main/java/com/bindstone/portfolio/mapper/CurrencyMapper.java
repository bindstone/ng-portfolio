package com.bindstone.portfolio.mapper;

import com.bindstone.portfolio.entity.Currency;
import com.bindstone.portfolio.mapper.dto.CurrencyDto;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

/**
 * JPA/DTO Mapper for CURRENCY
 */
@Component
public class CurrencyMapper implements DtoMapper<Currency, CurrencyDto> {

    /**
     * JPA to DTO Mapper
     *
     * @param object JPA Object
     * @return DTO Object
     */
    @Override
    public CurrencyDto toDto(Currency object) {
        Validate.notNull(object);
        return CurrencyDto.builder()
                .id(object.getId())
                .iso(object.getIso())
                .name(object.getName())
                .exchange(object.getExchange())
                .build();
    }

    /**
     * JPA to DTO Mapper
     *
     * @param objectDto DTO Object
     * @return JPA Object
     */
    @Override
    public Currency fromDto(CurrencyDto objectDto) {
        Validate.notNull(objectDto);
        return Currency.builder()
                .id(objectDto.getId())
                .iso(objectDto.getIso())
                .name(objectDto.getName())
                .exchange(objectDto.getExchange())
                .build();
    }
}
