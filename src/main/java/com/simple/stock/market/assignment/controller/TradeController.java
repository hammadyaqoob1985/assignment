package com.simple.stock.market.assignment.controller;

import com.simple.stock.market.assignment.calculator.GBCECalculator;
import com.simple.stock.market.assignment.calculator.VolumeWeightedStockPriceCalculator;
import com.simple.stock.market.assignment.dao.TradeTracker;
import com.simple.stock.market.assignment.model.StockSymbol;
import com.simple.stock.market.assignment.model.Trade;
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
        tradeTracker.recordTrade(stockSymbol, trade);
    }

    @RequestMapping(value = "/calculateVolumeWeightedStockPrice", method = RequestMethod.GET)
    @ResponseBody
    public double calculateVolumeWeightedStockPrice(@RequestParam StockSymbol stockSymbol, @RequestParam int stockMinutesBackTrack) {
        return volumeWeightedStockPriceCalculator.calculateVolumeWeightedStockPrice(stockSymbol, stockMinutesBackTrack);
    }

    @RequestMapping(value = "/calculateGBCE", method = RequestMethod.GET)
    @ResponseBody
    public double calculateGBCE() {
        return gbceCalculator.calculateGeometricMean();
    }
}
