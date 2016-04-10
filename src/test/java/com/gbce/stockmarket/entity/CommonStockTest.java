package com.gbce.stockmarket.entity;

import org.junit.Test;

import java.math.BigDecimal;

import static com.gbce.stockmarket.Constants.CENTS_PRECISION;
import static com.gbce.stockmarket.Constants.ROUNDING_POLICY;
import static junit.framework.TestCase.assertEquals;

public class CommonStockTest {

    @Test
    public void createStockWhenValidInput() {
        Stock stock = new CommonStock("TEA", 100, 20);
        assertEquals("The stock symbol should be TEA", "TEA", stock.getStockSymbol());
        assertEquals("The stock type should be Common", StockType.COMMON, stock.getStockType());
        assertEquals("The stock dividend should be 20.0", new BigDecimal(20.0).setScale(CENTS_PRECISION, ROUNDING_POLICY), stock.getDividend());
        assertEquals("The stock par value should be 100.0", new BigDecimal(100.0).doubleValue(), stock.getParValue());
    }


    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenSymbolIsBlank() {
        new CommonStock("", 100, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenSymbolIsInvalid() {
        new CommonStock(null, 100, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenParValueIsNegative() {
        new CommonStock("TEA", -100, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenParValueIsZero() {
        new CommonStock("TEA", 0, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenDividendIsNegative() {
        new CommonStock("TEA", 100, -20);
    }

    @Test
    public void createStockWhenDividendIsZero() {
        Stock stock = new CommonStock("TEA", 100, 0);
        assertEquals("The stock symbol should be TEA", "TEA", stock.getStockSymbol());
        assertEquals("The stock type should be Preferred", StockType.COMMON, stock.getStockType());
        assertEquals("The stock dividend should be 0.00", BigDecimal.ZERO.setScale(CENTS_PRECISION, ROUNDING_POLICY), stock.getDividend());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenSymbolIsEmptyDividendIsNegativeParValueIsNegative() {
        new CommonStock("", -100, -12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenSymbolIsNullDividendIsNegativeParValueIsZero() {
        new CommonStock(null, 0, -1);
    }
}
