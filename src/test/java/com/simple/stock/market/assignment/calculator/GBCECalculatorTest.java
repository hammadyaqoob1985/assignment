package com.simple.stock.market.assignment.calculator;

import com.simple.stock.market.assignment.dao.TradeTracker;
import com.simple.stock.market.assignment.model.Trade;
import com.simple.stock.market.assignment.model.TradeTypeIndicator;
import org.apache.commons.math3.stat.StatUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class GBCECalculatorTest {

    @Mock
    private TradeTracker tradeTracker;

    private GBCECalculator testee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Trade firstTrade = new Trade(ZonedDateTime.now().minusMinutes(4), 6, TradeTypeIndicator.SELL, 2);
        Trade secondTrade = new Trade(ZonedDateTime.now().minusMinutes(3), 1, TradeTypeIndicator.SELL, 2);
        Trade thirdTrade = new Trade(ZonedDateTime.now().minusMinutes(2), 2, TradeTypeIndicator.SELL, 2);
        Trade fourthTrade = new Trade(ZonedDateTime.now().minusMinutes(1), 1, TradeTypeIndicator.SELL, 2);
        when(tradeTracker.getAllTrades()).thenReturn(Arrays.asList(firstTrade, secondTrade, thirdTrade, fourthTrade));
        testee = new GBCECalculator(tradeTracker);
    }

    @Test
    void calculateGBCE() {
        double[] expectedPrices = new double [] {2.0,2.0,2.0,2.0};
        assertThat(testee.calculateGeometricMean()).isEqualTo(StatUtils.geometricMean(expectedPrices));
    }
}