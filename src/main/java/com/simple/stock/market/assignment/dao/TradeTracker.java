package com.simple.stock.market.assignment.dao;

import com.simple.stock.market.assignment.exception.StockNotFoundException;
import com.simple.stock.market.assignment.model.StockSymbol;
import com.simple.stock.market.assignment.model.Trade;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TradeTracker {
    private final ConcurrentHashMap<StockSymbol, List<Trade>> tradeMap;

    public TradeTracker() {
        this.tradeMap = new ConcurrentHashMap<>();
    }

    public void recordTrade(StockSymbol stockSymbol, Trade trade) {
        if(tradeMap.containsKey(stockSymbol)) {
            tradeMap.get(stockSymbol).add(trade);
        } else {
            List<Trade> tradesForStock =  new ArrayList<>();
            tradesForStock.add(trade);
            tradeMap.put(stockSymbol, tradesForStock);
        }
    }

    public List<Trade> getTradesForStock(StockSymbol stockSymbol) {
        if(tradeMap.containsKey(stockSymbol)) {
            return tradeMap.get(stockSymbol);
        } else {
           throw new StockNotFoundException();
        }
    }

    public List<Trade> getAllTrades() {
        return tradeMap.entrySet().stream().flatMap(e -> e.getValue().stream()).collect(Collectors.toList());
    }
}
