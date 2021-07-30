package com.simple.stock.market.assignment.stocks.calculator;

import com.simple.stock.market.assignment.stocks.exceptions.InvalidDividendException;
import com.simple.stock.market.assignment.stocks.model.Stock;
import com.simple.stock.market.assignment.common.model.StockSymbol;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PERatioCalculatorImpl implements PERatioCalculator {
    private final List<Stock> stocks;

    public PERatioCalculatorImpl(@Qualifier("stocks") List<Stock> stocks) {
        this.stocks = stocks;
    }

    public double calculatePERatio(StockSymbol stockSymbol, double price) {
        Stock selectedStock = stocks.stream()
                .filter(stock -> stock.getSymbol().equals(stockSymbol)).findFirst().get();

        if(selectedStock.getLastDividend() == 0) {
            throw new InvalidDividendException();
        }

        return price / selectedStock.getLastDividend();
    }
}
