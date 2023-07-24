package com.example.lotwork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LotExсeption {
    @ExceptionHandler(LotNotFoundException.class)
    public ResponseEntity<?> handleLotNotFound(LotNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("лот с id = %d не найден".formatted(e.getId()));
    }

    @ExceptionHandler(BetNotFoundException.class)
    public ResponseEntity<?> handleBidNotFound(LotNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("нет ставки");
    }
}
