package com.example.lotwork.exception;

public class LotNotFoundException extends RuntimeException {
    private final int id;
    public LotNotFoundException(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
