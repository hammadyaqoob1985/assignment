package com.simple.stock.market.assignment.config;

import com.simple.stock.market.assignment.exception.InvalidDividendException;
import com.simple.stock.market.assignment.exception.InvalidPriceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class StockControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<Object> handleInvalidPrice() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Invalid price. Price entered must be greater than 0");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDividendException.class)
    public ResponseEntity<Object> handleInvalidDividend() {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Last dividend is 0. Cannot calculate PE Ration");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
