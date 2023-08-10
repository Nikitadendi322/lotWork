package com.example.lotwork.model;

public enum Status {
    CREATED(0),
    STARTED(1),
    STOPPED(2);

    private final int statusId;

    Status(int statusNumber) {
        this.statusId = statusNumber;
    }

    public int getStatusNumber() {
        return statusId;
    }
}