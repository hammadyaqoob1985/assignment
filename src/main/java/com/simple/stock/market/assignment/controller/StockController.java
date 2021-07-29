package com.simple.stock.market.assignment.controller;

import com.simple.stock.market.assignment.calculator.DividendYieldCalculator;
import com.simple.stock.market.assignment.calculator.PERatioCalculator;
import com.simple.stock.market.assignment.model.StockSymbol;
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
