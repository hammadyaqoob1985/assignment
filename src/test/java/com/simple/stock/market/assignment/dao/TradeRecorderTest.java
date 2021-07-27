package com.simple.stock.market.assignment.dao;

import com.simple.stock.market.assignment.exception.StockNotFoundException;
import com.simple.stock.market.assignment.model.Trade;
import com.simple.stock.market.assignment.model.TradeTypeIndicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static com.simple.stock.market.assignment.model.StockSymbol.ALE;
import static com.simple.stock.market.assignment.model.StockSymbol.TEA;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TradeRecorderTest {

    private TradeRecorder testee;
    @BeforeEach
    void setUp() {
        testee =  new TradeRecorder();
    }

    @Test
    void saveAndRetrieveTrades() {
        Trade teaTrade = new Trade(ZonedDateTime.now(), 3, TradeTypeIndicator.BUY, 12);
        Trade firstAleTrade = new Trade(ZonedDateTime.now(), 5, TradeTypeIndicator.BUY, 12);
        Trade secondAleTrade = new Trade(ZonedDateTime.now(), 3, TradeTypeIndicator.SELL, 12);
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
    void testExceptionThrownWhenStockSymbolNotFound() {
        assertThrows(StockNotFoundException.class, () -> {
            testee.getTradesForStock(TEA);
        });
    }
}