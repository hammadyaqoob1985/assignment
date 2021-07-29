package com.simple.stock.market.assignment.trades.dao;

import com.simple.stock.market.assignment.trades.exception.NoTradesException;
import com.simple.stock.market.assignment.trades.model.Trade;
import com.simple.stock.market.assignment.trades.model.TradeTypeIndicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.simple.stock.market.assignment.common.model.StockSymbol.ALE;
import static com.simple.stock.market.assignment.common.model.StockSymbol.TEA;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TradeTrackerTest {

    Trade teaTrade;
    Trade firstAleTrade;
    Trade secondAleTrade;

    private TradeTracker testee;
    @BeforeEach
    void setUp() {
        teaTrade = new Trade(3, TradeTypeIndicator.BUY, 12);
        firstAleTrade = new Trade(5, TradeTypeIndicator.BUY, 12);
        secondAleTrade = new Trade(3, TradeTypeIndicator.SELL, 12);

        testee =  new TradeTracker();
    }

    @Test
    void saveAndRetrieveTrades() {
        testee.recordTrade(ALE, firstAleTrade);
        testee.recordTrade(ALE, secondAleTrade);
        testee.recordTrade(TEA, teaTrade);

        List<Trade> teaTrades =  testee.getTradesForStock(TEA);
        List<Trade> aleTrades =  testee.getTradesForStock(ALE);

        assertThat(teaTrades).hasSize(1);
        assertThat(teaTrades).containsExactly(teaTrade);

        assertThat(aleTrades).hasSize(2);
        assertThat(aleTrades).containsExactlyInAnyOrder(firstAleTrade, secondAleTrade);
    }

    @Test
    void getAllTrades() {
        testee.recordTrade(ALE, firstAleTrade);
        testee.recordTrade(ALE, secondAleTrade);
        testee.recordTrade(TEA, teaTrade);

        List<Trade> trades = testee.getAllTrades();
        assertThat(trades).containsExactlyInAnyOrder(firstAleTrade, secondAleTrade, teaTrade);

    }

    @Test
    void testExceptionThrownWhenStockSymbolNotFound() {
        assertThrows(NoTradesException.class, () -> testee.getTradesForStock(TEA));
    }
}