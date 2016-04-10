package com.gbce.stocktrading.entity;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class TradeTest {
    @Test
    public void createTradeWhenValidInput() {
        Stock stock = new CommonStock("TEA", 100, 20);
        Trade trade = new Trade(stock, Direction.BUY, new BigDecimal(20), 12);
        assertEquals("The stock symbol should be TEA", "TEA", trade.getStock().getStockSymbol());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenStockIsInvalid() {
        new Trade(null, Direction.BUY, new BigDecimal(20), 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenDirectionIsInvalid() {
        Stock stock = new CommonStock("TEA", 100, 20);
        new Trade(stock, null, new BigDecimal(20), 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenPriceIsInvalid() {
        Stock stock = new CommonStock("TEA", 100, 20);
        new Trade(stock, Direction.BUY, null, 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenPriceIsZero() {
        Stock stock = new CommonStock("TEA", 100, 20);
        new Trade(stock, Direction.BUY, BigDecimal.ZERO, 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenPriceIsNegative() {
        Stock stock = new CommonStock("TEA", 100, 20);
        new Trade(stock, Direction.BUY, new BigDecimal(-132), 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenQuantityIsZero() {
        Stock stock = new CommonStock("TEA", 100, 20);
        new Trade(stock, Direction.BUY, new BigDecimal(20), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionWhenQuantityIsNegative() {
        Stock stock = new CommonStock("TEA", 100, 20);
        new Trade(stock, Direction.BUY, new BigDecimal(20), -12);
    }

}
