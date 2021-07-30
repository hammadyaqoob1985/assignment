package com.simple.stock.market.assignment.trades.dao;

import com.simple.stock.market.assignment.common.model.StockSymbol;
import com.simple.stock.market.assignment.trades.model.Trade;

import java.util.List;

public interface TradeTracker {

    void recordTrade(StockSymbol stockSymbol, Trade trade);

    List<Trade> getTradesForStock(StockSymbol stockSymbol);

    List<Trade> getAllTrades();
}
