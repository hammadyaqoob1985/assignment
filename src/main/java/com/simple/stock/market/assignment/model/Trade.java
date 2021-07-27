package com.simple.stock.market.assignment.model;

import java.time.ZonedDateTime;

public class Trade {
    private final ZonedDateTime timestamp;
    private final int shares;
    private final TradeTypeIndicator tradeTypeIndicator;
    private final double price;

    public Trade(ZonedDateTime timestamp, int shares, TradeTypeIndicator tradeTypeIndicator, double price) {
        this.timestamp = timestamp;
        this.shares = shares;
        this.tradeTypeIndicator = tradeTypeIndicator;
        this.price = price;
    }
}
