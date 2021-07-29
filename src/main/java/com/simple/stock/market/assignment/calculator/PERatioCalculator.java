package com.simple.stock.market.assignment.calculator;

import com.simple.stock.market.assignment.exception.InvalidDividendException;
import com.simple.stock.market.assignment.model.Stock;
import com.simple.stock.market.assignment.model.StockSymbol;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PERatioCalculator {
    private final List<Stock> stocks;

    public PERatioCalculator(@Qualifier("stocks") List<Stock> stocks) {
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
