package com.simple.stock.market.assignment.calculator;

import com.simple.stock.market.assignment.exception.InvalidPriceException;
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

class DividendYieldCalculatorTest {

    private List<Stock> stocks;
    private DividendYieldCalculator testeeCalculator;
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
        testeeCalculator = new DividendYieldCalculator(stocks);
    }

    @Test
    void returnCorrectValueForCommonStock() {
        assertThat(testeeCalculator.calculateDividendYield(POP, 20)).isEqualTo(popStock.getLastDividend() / 20.0);
    }

    @Test
    void returnCorrectValueForPreferredStock() {
        assertThat(testeeCalculator.calculateDividendYield(GIN, 20)).isEqualTo((ginStock.getFixedDividend() *  ginStock.getParValue())/ 20.0);
    }

    @Test
    void throwsErrorWhenInvalidPriceEntered() {
        assertThrows(InvalidPriceException.class, () -> {
            testeeCalculator.calculateDividendYield(GIN, 0);
        });
    }
}