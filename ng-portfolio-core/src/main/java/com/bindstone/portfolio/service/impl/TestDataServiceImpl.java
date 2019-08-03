package com.bindstone.portfolio.service.impl;

import com.bindstone.portfolio.entity.Currency;
import com.bindstone.portfolio.entity.CurrencyHistory;
import com.bindstone.portfolio.repository.CurrencyHistoryRepository;
import com.bindstone.portfolio.repository.CurrencyRepository;
import com.bindstone.portfolio.service.CurrencyService;
import com.bindstone.portfolio.service.TestDataService;
import com.bindstone.portfolio.tools.NumberHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestDataServiceImpl implements TestDataService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrencyHistoryRepository currencyHistoryRepository;

    @Autowired
    CurrencyService currencyService;

    @Override
    @Transactional
    public void generate() {

        // Clear all
        currencyHistoryRepository.deleteAll();
        currencyRepository.deleteAll();

        createCurrency("GEC", "Global Earth Currency", new BigDecimal(300), new BigDecimal(350));
        createCurrency("ALD", "Altairian dollar", new BigDecimal(500), new BigDecimal(650));
        createCurrency("CLF", "Clockwork frog", new BigDecimal(80), new BigDecimal(90));
    }

    private void createCurrency(String iso, String name, BigDecimal min, BigDecimal max) {
        Currency currency = Currency.builder()
                .iso(iso)
                .name(name)
                .build();

        LocalDateTime date = LocalDateTime.now();
        List<CurrencyHistory> history = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            CurrencyHistory currencyHistory =
                    CurrencyHistory.builder()
                            .currency(currency)
                            .valueDate(date.minus(i, ChronoUnit.DAYS))
                            .rate(NumberHelper.random(min, max))
                            .build();
            history.add(currencyHistory);
        }

        currency.setExchange(history.get(history.size() - 1).getRate());

        currencyService.save(currency);
    }
}
