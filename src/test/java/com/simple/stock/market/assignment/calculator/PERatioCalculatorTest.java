package com.simple.stock.market.assignment.calculator;

import com.simple.stock.market.assignment.exception.InvalidDividendException;
import com.simple.stock.market.assignment.model.Stock;
import com.simple.stock.market.assignment.model.StockBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.simple.stock.market.assignment.model.StockSymbol.*;
import static com.simple.stock.market.assignment.model.StockType.COMMON;
import static com.simple.stock.market.assignment.model.StockType.PREFERRED;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PERatioCalculatorTest {

    private List<Stock> stocks;
    private PERatioCalculator testeeCalculator;
    Stock teaStock;
    Stock popStock;
    Stock aleStock;
    Stock ginStock;
    Stock joeStock;

    @BeforeEach
    void setUp() {
        teaStock = new StockBuilder().setSymbol(TEA).setStockType(COMMON).setLastDividend(0).setParValue(100).createStock();
        popStock = new StockBuilder().setSymbol(POP).setStockType(COMMON).setLastDividend(8).setParValue(100).createStock();
        aleStock = new StockBuilder().setSymbol(ALE).setStockType(COMMON).setLastDividend(23).setParValue(60).createStock();
        ginStock = new StockBuilder().setSymbol(GIN).setStockType(PREFERRED).setLastDividend(8).setFixedDividend(0.02).setParValue(100).createStock();
        joeStock = new StockBuilder().setSymbol(JOE).setStockType(COMMON).setLastDividend(13).setParValue(250).createStock();
        stocks = Arrays.asList(teaStock, popStock, aleStock, ginStock, joeStock);
        testeeCalculator = new PERatioCalculator(stocks);
    }

    @Test
    void calculatesPERatioCorrectly() {
        assertThat(testeeCalculator.calculatePERatio(ALE, 20)).isEqualTo(20.0 / aleStock.getLastDividend());
    }

    @Test
    void throwsExceptionWhenDividendIsZero() {
        assertThrows(InvalidDividendException.class, () -> {
            testeeCalculator.calculatePERatio(TEA, 20);
        });
    }
}