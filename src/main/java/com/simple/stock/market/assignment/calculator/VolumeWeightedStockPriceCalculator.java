package com.simple.stock.market.assignment.calculator;

import com.simple.stock.market.assignment.dao.TradeTracker;
import com.simple.stock.market.assignment.model.StockSymbol;
import com.simple.stock.market.assignment.model.Trade;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VolumeWeightedStockPriceCalculator {

    private final TradeTracker tradeTracker;
    public final int stockMinutesBackTrack;

    public VolumeWeightedStockPriceCalculator(TradeTracker tradeTracker, int stockMinutesBackTrack) {
        this.tradeTracker = tradeTracker;
        this.stockMinutesBackTrack = stockMinutesBackTrack;
    }

    public double calculateVolumeWeightedStockPrice(StockSymbol stockSymbol) {
        ZonedDateTime tradeTimeCutOff = ZonedDateTime.now().minusMinutes(stockMinutesBackTrack);
        List<Trade> relevantTrades = tradeTracker.getTradesForStock(stockSymbol)
                .parallelStream().filter(trade -> trade.getTimestamp().isAfter(tradeTimeCutOff)).collect(Collectors.toList());

        Double numerator = relevantTrades.parallelStream().mapToDouble(trade -> trade.getPrice() * trade.getQuantity()).sum();
        Double denominator = relevantTrades.parallelStream().mapToDouble(Trade::getQuantity).sum();

        return numerator / denominator;

    }
}
