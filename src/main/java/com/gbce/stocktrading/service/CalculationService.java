package com.gbce.stocktrading.service;

import com.gbce.stocktrading.exception.UnknownGBCEAllShareIndex;
import com.gbce.stocktrading.exception.UnknownPEException;
import com.gbce.stocktrading.exception.UnknownStockException;


public interface CalculationService {
    double getDividendYield(String stockSymbol, double price) throws UnknownStockException;

    double getPERatio(String stockSymbol, double price) throws UnknownStockException, UnknownPEException;

    double getVolumeWeightedPrice(String stockSymbol, int minutes) throws UnknownStockException;

    double getGBCEAllShareIndex() throws UnknownGBCEAllShareIndex;


}
