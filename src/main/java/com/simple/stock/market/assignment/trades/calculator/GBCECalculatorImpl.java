package com.simple.stock.market.assignment.trades.calculator;

import com.simple.stock.market.assignment.trades.dao.TradeTracker;
import com.simple.stock.market.assignment.trades.exception.NoTradesException;
import com.simple.stock.market.assignment.trades.model.Trade;
import org.apache.commons.math3.stat.StatUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GBCECalculatorImpl implements GBCECalculator {

    private final TradeTracker tradeTracker;

    public GBCECalculatorImpl(TradeTracker tradeTracker) {
        this.tradeTracker = tradeTracker;
    }

    public double calculateGeometricMean() {
        List<Trade> allTrades =  tradeTracker.getAllTrades();
        if(allTrades.isEmpty()) {
            throw new NoTradesException();
        }
        List<Double> allPrices = allTrades.stream().map(Trade::getPrice).collect(Collectors.toList());
        double[] array = allPrices.stream().mapToDouble(d -> d).toArray();
        return StatUtils.geometricMean(array);
    }
}
