package com.simple.stock.market.assignment.stocks.config;

import com.simple.stock.market.assignment.stocks.model.Stock;
import com.simple.stock.market.assignment.stocks.model.StockBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

import static com.simple.stock.market.assignment.common.model.StockSymbol.*;
import static com.simple.stock.market.assignment.stocks.model.StockType.COMMON;
import static com.simple.stock.market.assignment.stocks.model.StockType.PREFERRED;

@Configuration
public class StockConfig {

    @Bean("stocks")
    public List<Stock> stocks() {
        Stock teaStock = new StockBuilder().setSymbol(TEA).setStockType(COMMON).setLastDividend(0).setParValue(100).createStock();
        Stock popStock = new StockBuilder().setSymbol(POP).setStockType(COMMON).setLastDividend(8).setParValue(100).createStock();
        Stock aleStock = new StockBuilder().setSymbol(ALE).setStockType(COMMON).setLastDividend(23).setParValue(60).createStock();
        Stock ginStock = new StockBuilder().setSymbol(GIN).setStockType(PREFERRED).setLastDividend(8).setFixedDividend(0.02).setParValue(100).createStock();
        Stock joeStock = new StockBuilder().setSymbol(JOE).setStockType(COMMON).setLastDividend(13).setParValue(250).createStock();
        return Arrays.asList(teaStock, popStock, aleStock, ginStock, joeStock);
    }
}
