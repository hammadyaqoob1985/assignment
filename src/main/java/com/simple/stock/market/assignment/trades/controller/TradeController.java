package com.simple.stock.market.assignment.trades.controller;

import com.simple.stock.market.assignment.common.model.StockSymbol;
import com.simple.stock.market.assignment.trades.calculator.GBCECalculator;
import com.simple.stock.market.assignment.trades.calculator.VolumeWeightedStockPriceCalculator;
import com.simple.stock.market.assignment.trades.dao.TradeTracker;
import com.simple.stock.market.assignment.trades.exception.InvalidTradeException;
import com.simple.stock.market.assignment.trades.model.Trade;
import org.springframework.web.bind.annotation.*;

@RestController
public class TradeController {

    private final VolumeWeightedStockPriceCalculator volumeWeightedStockPriceCalculator;
    private final GBCECalculator gbceCalculator;
    private final TradeTracker tradeTracker;

    public TradeController(VolumeWeightedStockPriceCalculator volumeWeightedStockPriceCalculator,
                           GBCECalculator gbceCalculator,
                           TradeTracker tradeTracker) {
        this.volumeWeightedStockPriceCalculator = volumeWeightedStockPriceCalculator;
        this.gbceCalculator = gbceCalculator;
        this.tradeTracker = tradeTracker;
    }

    @RequestMapping(value = "/addTrade", method = RequestMethod.POST)
    public void addTrade(@RequestParam StockSymbol stockSymbol, @RequestBody Trade trade) {
        if(trade.getPrice() <= 0 || trade.getQuantity() <= 0) {
            throw new InvalidTradeException();
        }
        tradeTracker.recordTrade(stockSymbol, trade);
    }

    @RequestMapping(value = "/calculateVolumeWeightedStockPrice", method = RequestMethod.GET)
    @ResponseBody
    public double calculateVolumeWeightedStockPrice(@RequestParam StockSymbol stockSymbol, @RequestParam int tradeMinutesBackTrack) {
        return volumeWeightedStockPriceCalculator.calculateVolumeWeightedStockPrice(stockSymbol, tradeMinutesBackTrack);
    }

    @RequestMapping(value = "/calculateGBCE", method = RequestMethod.GET)
    @ResponseBody
    public double calculateGBCE() {
        return gbceCalculator.calculateGeometricMean();
    }
}
