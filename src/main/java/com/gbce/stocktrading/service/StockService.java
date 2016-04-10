package com.gbce.stocktrading.service;

import com.gbce.stocktrading.entity.Stock;
import com.gbce.stocktrading.exception.UnknownStockException;

import java.util.Collection;


public interface StockService {
    Stock getStock(String stockSymbol) throws UnknownStockException;

    void addStock(Stock stock);

    Collection<Stock> getAllStocksOfExchange();
}
