package com.simple.stock.market.assignment.stocks.calculator;

import com.simple.stock.market.assignment.stocks.exceptions.InvalidPriceException;
import com.simple.stock.market.assignment.stocks.model.Stock;
import com.simple.stock.market.assignment.common.model.StockSymbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.simple.stock.market.assignment.stocks.model.StockType.COMMON;

@Service
public class DividendYieldCalculatorImpl implements DividendYieldCalculator {

    private final List<Stock> stocks;

    @Autowired
    public DividendYieldCalculatorImpl(@Qualifier("stocks") List<Stock> stocks) {
        this.stocks = stocks;
    }

    public double calculateDividendYield(StockSymbol stockSymbol, double price) {
        if (price <= 0) {
            throw new InvalidPriceException();
        }
        Stock selectedStock = stocks.stream()
                .filter(stock -> stock.getSymbol().equals(stockSymbol)).findFirst().get();

        if(selectedStock.getStockType().equals(COMMON)) {
            return selectedStock.getLastDividend() / price;
        } else {
            return (selectedStock.getFixedDividend() * selectedStock.getParValue() )/ price;
        }
    }
}
