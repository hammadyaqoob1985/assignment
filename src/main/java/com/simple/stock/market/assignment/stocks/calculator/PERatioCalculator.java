package com.simple.stock.market.assignment.stocks.calculator;

import com.simple.stock.market.assignment.common.model.StockSymbol;

public interface PERatioCalculator {

    double calculatePERatio(StockSymbol stockSymbol, double price);
}
