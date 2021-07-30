package com.simple.stock.market.assignment.stocks.controller;

import com.simple.stock.market.assignment.common.model.StockSymbol;
import com.simple.stock.market.assignment.stocks.calculator.DividendYieldCalculator;
import com.simple.stock.market.assignment.stocks.calculator.PERatioCalculator;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    private final DividendYieldCalculator dividendYieldCalculator;
    private final PERatioCalculator peRatioCalculator;

    public StockController(DividendYieldCalculator dividendYieldCalculator, PERatioCalculator peRatioCalculator) {
        this.dividendYieldCalculator = dividendYieldCalculator;
        this.peRatioCalculator = peRatioCalculator;
    }

    @RequestMapping(value = "/calculateDividendYield", method = RequestMethod.POST)
    @ResponseBody
    public double calculateDividendYield(@RequestParam StockSymbol stockSymbol, @RequestParam double price) {
        return dividendYieldCalculator.calculateDividendYield(stockSymbol, price);
    }

    @RequestMapping(value = "/calculatePERatio", method = RequestMethod.POST)
    @ResponseBody
    public double calculatePERatio(@RequestParam StockSymbol stockSymbol, @RequestParam double price) {
        return peRatioCalculator.calculatePERatio(stockSymbol, price);
    }
}
