package com.simple.stock.market.assignment.calculator;

import com.simple.stock.market.assignment.dao.TradeTracker;
import com.simple.stock.market.assignment.model.StockSymbol;
import com.simple.stock.market.assignment.model.Trade;
import com.simple.stock.market.assignment.model.TradeTypeIndicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VolumeWeightedStockPriceCalculatorTest {

    @Mock
    private TradeTracker tradeTracker;

    private VolumeWeightedStockPriceCalculator testee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Trade nonInclusiveTrade = new Trade(ZonedDateTime.now().minusMinutes(6), 3, TradeTypeIndicator.SELL, 5);
        Trade firstTrade = new Trade(ZonedDateTime.now().minusMinutes(4), 6, TradeTypeIndicator.SELL, 2);
        Trade secondTrade = new Trade(ZonedDateTime.now().minusMinutes(3), 1, TradeTypeIndicator.SELL, 2);
        Trade thirdTrade = new Trade(ZonedDateTime.now().minusMinutes(2), 2, TradeTypeIndicator.SELL, 2);
        Trade fourthTrade = new Trade(ZonedDateTime.now().minusMinutes(1), 1, TradeTypeIndicator.SELL, 2);
        when(tradeTracker.getTradesForStock(any(StockSymbol.class))).thenReturn(Arrays.asList(nonInclusiveTrade,
                firstTrade, secondTrade, thirdTrade, fourthTrade));
        testee = new VolumeWeightedStockPriceCalculator(tradeTracker, 5);
    }

    @Test
    void calculateVolumeWeightedStockPrice() {
        assertThat(testee.calculateVolumeWeightedStockPrice(StockSymbol.TEA)).isEqualTo(2);
    }
}