package com.simple.stock.market.assignment.trades.calculator;

import com.simple.stock.market.assignment.trades.dao.TradeTracker;
import com.simple.stock.market.assignment.trades.exception.NoTradesException;
import com.simple.stock.market.assignment.common.model.StockSymbol;
import com.simple.stock.market.assignment.trades.model.Trade;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolumeWeightedStockPriceCalculator {

    private final TradeTracker tradeTracker;

    public VolumeWeightedStockPriceCalculator(TradeTracker tradeTracker) {
        this.tradeTracker = tradeTracker;
    }

    public double calculateVolumeWeightedStockPrice(StockSymbol stockSymbol, int stockMinutesBackTrack) {
        ZonedDateTime tradeTimeCutOff = ZonedDateTime.now().minusMinutes(stockMinutesBackTrack);
        List<Trade> relevantTrades = tradeTracker.getTradesForStock(stockSymbol)
                .parallelStream().filter(trade -> trade.getTimestamp().isAfter(tradeTimeCutOff)).collect(Collectors.toList());
        if(relevantTrades.isEmpty()) {
            throw new NoTradesException();
        }
        Double numerator = relevantTrades.parallelStream().mapToDouble(trade -> trade.getPrice() * trade.getQuantity()).sum();
        Double denominator = relevantTrades.parallelStream().mapToDouble(Trade::getQuantity).sum();

        return numerator / denominator;

    }
}
