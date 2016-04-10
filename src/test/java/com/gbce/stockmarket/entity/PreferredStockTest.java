package com.gbce.stockmarket.entity;

import org.junit.Test;

import java.math.BigDecimal;

import static com.gbce.stockmarket.Constants.CENTS_PRECISION;
import static com.gbce.stockmarket.Constants.ROUNDING_POLICY;
import static junit.framework.TestCase.assertEquals;

public class PreferredStockTest {

    @Test
    public void createPreferredStockWhenValidInput() {
        Stock stock = new PreferredStock("TEA", 120, 20, 5);
        assertEquals("The stock symbol should be TEA", "TEA", stock.getStockSymbol());
        assertEquals("The stock type should be Preferred", StockType.PREFERRED, stock.getStockType());
        assertEquals("The stock par value should be 120.0", new BigDecimal(120.0).doubleValue(), stock.getParValue());
        assertEquals("The stock dividend should be 6.00", new BigDecimal(6.0).setScale(CENTS_PRECISION, ROUNDING_POLICY), stock.getDividend());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenSymbolIsEmptyWhileCreatingPreferredStock() {
        new PreferredStock("", 120, 20, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenSymbolIsInvalidWhileCreatingPreferredStock() {
        new PreferredStock(null, 120, 20, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenParValueIsNegativeWhileCreatingPreferredStock() {
        new PreferredStock("TEA", -120, 20, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenParValueIsZeroWhileCreatingPreferredStock() {
        new PreferredStock("TEA", 0, 20, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenDividendIsNegativeWhileCreatingPreferredStock() {
        new PreferredStock("TEA", 100, -20, 5);
    }

    @Test
    public void createPreferredStockWhenDividendIsZero() {
        Stock stock = new PreferredStock("TEA", 120, 0, 5);
        assertEquals("The stock symbol should be TEA", "TEA", stock.getStockSymbol());
        assertEquals("The stock type should be Preferred", StockType.PREFERRED, stock.getStockType());
        assertEquals("The stock dividend should be 6.00", new BigDecimal(6.0).setScale(CENTS_PRECISION, ROUNDING_POLICY), stock.getDividend());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenFixedDividendPercentageIsNegativeWhileCreatingPreferredStock() {
        new PreferredStock("TEA", 120, 20, -5);
    }

    @Test
    public void createPreferredStockWhenFixedDividendPercentageIsZero() {
        Stock stock = new PreferredStock("TEA", 120, 20, 0);
        assertEquals("The stock symbol should be TEA", "TEA", stock.getStockSymbol());
        assertEquals("The stock type should be Preferred", StockType.PREFERRED, stock.getStockType());
        assertEquals("The stock dividend should be 0.00", BigDecimal.ZERO.setScale(CENTS_PRECISION, ROUNDING_POLICY), stock.getDividend());
    }

}
