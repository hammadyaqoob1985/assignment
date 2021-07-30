package com.simple.stock.market.assignment.trades.calculator;

import com.simple.stock.market.assignment.trades.dao.TradeTrackerImpl;
import com.simple.stock.market.assignment.trades.model.Trade;
import com.simple.stock.market.assignment.trades.model.TradeTypeIndicator;
import org.apache.commons.math3.stat.StatUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class GBCECalculatorImplTest {

    @Mock
    private TradeTrackerImpl tradeTracker;

    private GBCECalculatorImpl testee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Trade firstTrade = new Trade(6, TradeTypeIndicator.SELL, 2);
        Trade secondTrade = new Trade( 1, TradeTypeIndicator.SELL, 2);
        Trade thirdTrade = new Trade(2, TradeTypeIndicator.SELL, 2);
        Trade fourthTrade = new Trade(1, TradeTypeIndicator.SELL, 2);
        when(tradeTracker.getAllTrades()).thenReturn(Arrays.asList(firstTrade, secondTrade, thirdTrade, fourthTrade));
        testee = new GBCECalculatorImpl(tradeTracker);
    }

    @Test
    void calculateGBCE() {
        double[] expectedPrices = new double [] {2.0,2.0,2.0,2.0};
        assertThat(testee.calculateGeometricMean()).isEqualTo(StatUtils.geometricMean(expectedPrices));
    }
}