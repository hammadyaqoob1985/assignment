package com.simple.stock.market.assignment.model;

import java.time.ZonedDateTime;

public class Trade {

    private final ZonedDateTime timestamp;
    private final int quantity;
    private final TradeTypeIndicator tradeTypeIndicator;
    private final double price;

    public Trade(ZonedDateTime timestamp, int quantity, TradeTypeIndicator tradeTypeIndicator, double price) {
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.tradeTypeIndicator = tradeTypeIndicator;
        this.price = price;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public int getQuantity() {
        return quantity;
    }

    public TradeTypeIndicator getTradeTypeIndicator() {
        return tradeTypeIndicator;
    }

    public double getPrice() {
        return price;
    }
}