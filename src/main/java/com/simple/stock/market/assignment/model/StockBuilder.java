package com.simple.stock.market.assignment.model;

public class StockBuilder {
    private StockSymbol symbol;
    private StockType stockType;
    private int lastDividend;
    private double fixedDividend;
    private int parValue;

    public StockBuilder setSymbol(StockSymbol symbol) {
        this.symbol = symbol;
        return this;
    }

    public StockBuilder setStockType(StockType stockType) {
        this.stockType = stockType;
        return this;
    }

    public StockBuilder setLastDividend(int lastDividend) {
        this.lastDividend = lastDividend;
        return this;
    }

    public StockBuilder setFixedDividend(double fixedDividend) {
        this.fixedDividend = fixedDividend;
        return this;
    }

    public StockBuilder setParValue(int parValue) {
        this.parValue = parValue;
        return this;
    }

    public Stock createStock() {
        return new Stock(symbol, stockType, lastDividend, fixedDividend, parValue);
    }
}