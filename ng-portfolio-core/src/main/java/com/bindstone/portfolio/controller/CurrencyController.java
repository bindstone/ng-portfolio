package com.bindstone.portfolio.controller;

import com.bindstone.portfolio.entity.Currency;
import com.bindstone.portfolio.mapper.CurrencyMapper;
import com.bindstone.portfolio.mapper.dto.CurrencyDto;
import com.bindstone.portfolio.service.CurrencyService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/currency")
public class CurrencyController {

    private CurrencyService currencyService;
    private CurrencyMapper currencyMapper;

    @Autowired
    public CurrencyController(CurrencyService currencyService, CurrencyMapper currencyMapper) {
        this.currencyService = currencyService;
        this.currencyMapper = currencyMapper;
    }

    @GetMapping
    public Flux<CurrencyDto> getList() {
        Flux<Currency> all = this.currencyService.getAll();

        return all.map(currencyMapper::toDto);
    }

    @GetMapping("/{id}")
    public Mono<CurrencyDto> getById(@PathVariable Long id) {

        return this.currencyService.getById(id).map(currencyMapper::toDto);
    }

    @PostMapping
    public void create(@RequestBody CurrencyDto currencyDto) {
        Validate.notNull(currencyDto, "Invalid parameter: [CurrencyDto]");

        currencyService.save(currencyMapper.fromDto(currencyDto));
    }

    @PutMapping
    public void update(@RequestBody CurrencyDto currencyDto) {
        Validate.notNull(currencyDto, "Invalid parameter: [CurrencyDto]");

        currencyService.save(currencyMapper.fromDto(currencyDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Validate.notNull(id, "Invalid parameter: [Id]");

        currencyService.deleteById(id);
    }

}
