package com.gbce.stocktrading.service;

import com.gbce.stocktrading.entity.Direction;
import com.gbce.stocktrading.entity.Stock;
import com.gbce.stocktrading.entity.Trade;
import com.gbce.stocktrading.exception.UnknownStockException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static com.gbce.stocktrading.Constants.CENTS_PRECISION;
import static com.gbce.stocktrading.Constants.ROUNDING_POLICY;


public class TradeServiceImpl implements TradeService {
    private final DatasourceService datasourceService;
    private final StockService stockService;


    public TradeServiceImpl(DatasourceService tradePersistenceService, StockService stockService) {
        this.datasourceService = tradePersistenceService;
        this.stockService = stockService;
    }

    @Override
    public void bookTrade(String stockSymbol, long quantity, String direction, double price) throws UnknownStockException {
        if (stockSymbol == null || stockSymbol.isEmpty() || quantity <= 0 || direction == null || direction.isEmpty()
                || price <= 0) {
            throw new IllegalArgumentException("Incorrect argument provided " + "stockSymbol=" + stockSymbol + " quantity=" + quantity +
                    " direction=" + direction + " price=" + price);
        }
        Stock stock = stockService.getStock(stockSymbol);

        datasourceService.storeTrade(new Trade(stock, Direction.valueOf(direction.toUpperCase()),
                new BigDecimal(price).setScale(CENTS_PRECISION, ROUNDING_POLICY), quantity));
    }

    @Override
    public Collection<Trade> getTradesWithinTimeWindow(String stockSymbol, double minutes) throws UnknownStockException {
        if (stockSymbol == null || stockSymbol.isEmpty() || minutes <= 0.0) {
            throw new IllegalArgumentException("Incorrect argument provided " + "stockSymbol=" + stockSymbol + " minutes=" + minutes);
        }
        long cutOffTimeStamp = (long) (new Date().getTime() - minutes * 60 * 1000);
        Collection<Trade> trades = getAllTradesForStock(stockSymbol);
        return trades.stream().filter(trade -> trade.getTimestamp() >= cutOffTimeStamp).collect(Collectors.toList());
    }


    @Override
    public Collection<Trade> getAllTradesForStock(String stockSymbol) throws UnknownStockException {
        Stock stock = stockService.getStock(stockSymbol);
        return datasourceService.getTradesForStock(stock);
    }
}
