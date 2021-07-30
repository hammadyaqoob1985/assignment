package com.simple.stock.market.assignment.stocks.controller;

import com.simple.stock.market.assignment.common.model.StockSymbol;
import com.simple.stock.market.assignment.stocks.calculator.DividendYieldCalculator;
import com.simple.stock.market.assignment.stocks.calculator.PERatioCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class StockControllerTest {

    @Mock
    private DividendYieldCalculator dividendYieldCalculator;
    @Mock
    private PERatioCalculator peRatioCalculator;
    
    private StockController testee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testee = new StockController(dividendYieldCalculator, peRatioCalculator);
    }

    @Test
    void verifyDividendYieldCalculatorCalled() {
        testee.calculateDividendYield(StockSymbol.TEA, 2);
        verify(dividendYieldCalculator).calculateDividendYield(StockSymbol.TEA, 2);
    }

    @Test
    void verifyPERatioCalculatorCalled() {
        testee.calculatePERatio(StockSymbol.TEA, 2);
        verify(peRatioCalculator).calculatePERatio(StockSymbol.TEA, 2);
    }
}