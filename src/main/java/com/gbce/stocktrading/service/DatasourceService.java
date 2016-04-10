package com.gbce.stocktrading.service;

import com.gbce.stocktrading.entity.Stock;
import com.gbce.stocktrading.entity.Trade;

import java.util.Collection;


interface DatasourceService {

    void storeTrade(Trade trade);

    void storeStock(Stock stock);

    Stock getStock(String stockSymbol);

    Collection<Trade> getTradesForStock(Stock stock);

    Collection<Stock> getAllStocks();

}
