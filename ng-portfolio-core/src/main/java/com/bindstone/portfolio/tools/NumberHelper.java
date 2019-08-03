package com.bindstone.portfolio.tools;

import java.math.BigDecimal;

public class NumberHelper {
    /**
     * Random Big Decimal value generator
     *
     * @param min min of range
     * @param max max of range
     * @return random value
     */
    public static BigDecimal random(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
