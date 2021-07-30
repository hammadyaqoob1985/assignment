package com.simple.stock.market.assignment.trades.controller;

import com.simple.stock.market.assignment.trades.calculator.GBCECalculator;
import com.simple.stock.market.assignment.trades.calculator.VolumeWeightedStockPriceCalculator;
import com.simple.stock.market.assignment.trades.dao.TradeTracker;
import com.simple.stock.market.assignment.trades.exception.InvalidTradeException;
import com.simple.stock.market.assignment.trades.model.Trade;
import com.simple.stock.market.assignment.trades.model.TradeTypeIndicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.simple.stock.market.assignment.common.model.StockSymbol.TEA;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

class TradeControllerTest {

    @Mock
    private VolumeWeightedStockPriceCalculator volumeWeightedStockPriceCalculator;

    @Mock
    private GBCECalculator gbceCalculator;

    @Mock
    private TradeTracker tradeTracker;

    private TradeController testee;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testee = new TradeController(volumeWeightedStockPriceCalculator, gbceCalculator, tradeTracker);
    }

    @Test
    void verifyExceptionThrownIfPriceIsInvalid() {
        Trade trade = new Trade(0, TradeTypeIndicator.BUY, 5.0);
        assertThrows(InvalidTradeException.class, () -> testee.addTrade(TEA, trade));
    }

    @Test
    void verifyExceptionThrownIfQuantityIsInvalid() {
        Trade trade = new Trade(1, TradeTypeIndicator.BUY, 0);
        assertThrows(InvalidTradeException.class, () -> testee.addTrade(TEA, trade));
    }

    @Test
    void verifyTradeTrackerCalledWhenSavingTrade() {
        Trade trade = new Trade(1, TradeTypeIndicator.BUY, 1);
        testee.addTrade(TEA, trade);
        verify(tradeTracker).recordTrade(TEA, trade);
    }

    @Test
    void verifyVolumeWeightedStockPriceCalculatorCalled() {
        testee.calculateVolumeWeightedStockPrice(TEA, 1);
        verify(volumeWeightedStockPriceCalculator).calculateVolumeWeightedStockPrice(TEA, 1);
    }

    @Test
    void verifyGbceCalculatorCalled() {
        testee.calculateGBCE();
        verify(gbceCalculator).calculateGeometricMean();
    }
}