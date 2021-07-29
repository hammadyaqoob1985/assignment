package com.simple.stock.market.assignment.trades.controller;

import com.simple.stock.market.assignment.trades.exception.InvalidTradeException;
import com.simple.stock.market.assignment.trades.exception.NoTradesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class TradeControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoTradesException.class)
    public ResponseEntity<Object> handleNoTrade() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "No trade has been made");

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @ExceptionHandler(InvalidTradeException.class)
    public ResponseEntity<Object> handleInvalidTrade() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Invalid Trade");

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
