package com.simple.stock.market.assignment.controller;

import com.simple.stock.market.assignment.calculator.DividendYieldCalculator;
import com.simple.stock.market.assignment.model.StockSymbol;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    private final DividendYieldCalculator dividendYieldCalculator;

    public StockController(DividendYieldCalculator dividendYieldCalculator) {
        this.dividendYieldCalculator = dividendYieldCalculator;
    }

    @RequestMapping(value = "/calculateDividendYield", method = RequestMethod.POST)
    @ResponseBody
    public double calculate(@RequestParam StockSymbol stockSymbol, @RequestParam double price) {
        return dividendYieldCalculator.calculateDividendYield(stockSymbol, price);
    }
}
