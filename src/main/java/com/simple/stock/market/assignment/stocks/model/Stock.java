package com.simple.stock.market.assignment.stocks.model;


import com.simple.stock.market.assignment.common.model.StockSymbol;

public class Stock {

    private final StockSymbol symbol;
    private final StockType stockType;
    private final int lastDividend;
    private final double fixedDividend;
    private final int parValue;


    public Stock(StockSymbol symbol, StockType stockType, int lastDividend, double fixedDividend, int parValue) {
        this.symbol = symbol;
        this.stockType = stockType;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    public StockSymbol getSymbol() {
        return symbol;
    }

    public StockType getStockType() {
        return stockType;
    }

    public int getLastDividend() {
        return lastDividend;
    }

    public double getFixedDividend() {
        return fixedDividend;
    }

    public int getParValue() {
        return parValue;
    }
}
