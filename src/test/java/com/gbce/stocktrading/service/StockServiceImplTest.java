package com.gbce.stocktrading.service;

import com.gbce.stocktrading.entity.CommonStock;
import com.gbce.stocktrading.entity.PreferredStock;
import com.gbce.stocktrading.entity.Stock;
import com.gbce.stocktrading.exception.UnknownStockException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

public class StockServiceImplTest {
    private static DatasourceService datasourceService;
    private static StockService stockService;


    @Before
    public void setUp() {
        instantiateServices();
        stockService.addStock(new CommonStock("TEA", 100, 0));
    }

    private void instantiateServices() {
        datasourceService = new CacheDatasourceServiceImpl();
        stockService = new StockServiceImpl(datasourceService);
    }

    @Test
    public void getStockWhenValidStockSymbol() throws UnknownStockException {
        assertEquals("The stock symbol should be TEA", "TEA", stockService.getStock("TEA").getStockSymbol());
    }

    @Test
    public void addStock() throws UnknownStockException {
        stockService.addStock(new CommonStock("APPL", 100, 0));
        assertEquals("The stock symbol should be APPL", "APPL", stockService.getStock("APPL").getStockSymbol());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addStockTestWhenInvalidSymbolProvided() throws UnknownStockException {
        stockService.addStock(new CommonStock("", 100, 0));
    }


    @Test
    public void getAllStocksOfExchange() {
        instantiateServices();
        stockService.addStock(new CommonStock("ABC", 100, 0));
        stockService.addStock(new CommonStock("XYZ", 100, 0));
        stockService.addStock(new CommonStock("JPM", 100, 0));
        stockService.addStock(new PreferredStock("GOOG", 120, 0, 3));
        Collection<Stock> stocks = stockService.getAllStocksOfExchange();
        assertEquals("Number of stocks in exchange should be equal to ", 4, stocks.size());
    }


}
