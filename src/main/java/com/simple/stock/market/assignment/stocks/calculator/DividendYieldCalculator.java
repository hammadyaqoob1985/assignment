package com.simple.stock.market.assignment.stocks.calculator;

import com.simple.stock.market.assignment.common.model.StockSymbol;

public interface DividendYieldCalculator {

    double calculateDividendYield(StockSymbol stockSymbol, double price);

}
