package com.simple.stock.market.assignment.trades.calculator;

import com.simple.stock.market.assignment.common.model.StockSymbol;
import com.simple.stock.market.assignment.trades.dao.TradeTrackerImpl;
import com.simple.stock.market.assignment.trades.exception.NoTradesException;
import com.simple.stock.market.assignment.trades.model.Trade;
import com.simple.stock.market.assignment.trades.model.TradeTypeIndicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;

import static com.simple.stock.market.assignment.common.model.StockSymbol.TEA;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VolumeWeightedStockPriceCalculatorImplTest {

    @Mock
    private TradeTrackerImpl tradeTracker;

    private VolumeWeightedStockPriceCalculatorImpl testee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testee = new VolumeWeightedStockPriceCalculatorImpl(tradeTracker);
    }

    @Test
    void calculateVolumeWeightedStockPrice() throws InterruptedException {
        Trade nonIncludedTrade = new Trade( 6, TradeTypeIndicator.SELL, 6);

        Thread.sleep(60000);

        Trade firstTrade = new Trade( 6, TradeTypeIndicator.SELL, 2);
        Trade secondTrade = new Trade(1, TradeTypeIndicator.SELL, 2);
        Trade thirdTrade = new Trade( 2, TradeTypeIndicator.SELL, 2);
        Trade fourthTrade = new Trade( 1, TradeTypeIndicator.SELL, 2);
        when(tradeTracker.getTradesForStock(any(StockSymbol.class))).thenReturn(Arrays.asList(
                nonIncludedTrade, firstTrade, secondTrade, thirdTrade, fourthTrade));

        assertThat(testee.calculateVolumeWeightedStockPrice(TEA, 1)).isEqualTo(2);
    }

    @Test
    void testExceptionThrownWhenNoTrades() {
        when(tradeTracker.getTradesForStock(any(StockSymbol.class))).thenReturn(Collections.emptyList());
        assertThrows(NoTradesException.class, () -> testee.calculateVolumeWeightedStockPrice(TEA, 1));
    }
}