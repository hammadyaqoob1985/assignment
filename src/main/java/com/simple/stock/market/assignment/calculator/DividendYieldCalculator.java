package com.simple.stock.market.assignment.calculator;

import com.simple.stock.market.assignment.exception.InvalidPriceException;
import com.simple.stock.market.assignment.model.Stock;
import com.simple.stock.market.assignment.model.StockSymbol;

import java.util.List;
import java.util.Optional;

import static com.simple.stock.market.assignment.model.StockType.COMMON;

public class DividendYieldCalculator {

    private final List<Stock> stocks;

    public DividendYieldCalculator(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public double calculateDividendYield(StockSymbol stockSymbol, double price) {
        if (price <= 0) {
            throw new InvalidPriceException();
        }
        Optional<Stock> selectedStockOptional = stocks.stream().filter(stock -> stock.getSymbol().equals(stockSymbol)).findFirst();
        Stock selectedStock = selectedStockOptional.get();
        if(selectedStock.getStockType().equals(COMMON)) {
            return selectedStock.getLastDividend() / price;
        } else {
            return (selectedStock.getFixedDividend() * selectedStock.getParValue() )/ price;
        }
    }
}
