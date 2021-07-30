package com.simple.stock.market.assignment.trades.calculator;

import com.simple.stock.market.assignment.common.model.StockSymbol;

public interface VolumeWeightedStockPriceCalculator {

    double calculateVolumeWeightedStockPrice(StockSymbol stockSymbol, int stockMinutesBackTrack);
}
