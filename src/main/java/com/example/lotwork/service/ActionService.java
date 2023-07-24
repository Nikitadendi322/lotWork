package com.example.lotwork.service;

import com.example.lotwork.DTO.FullInfoLot;
import com.example.lotwork.model.Bet;
import com.example.lotwork.model.Lot;
import com.example.lotwork.model.Status;

import java.io.IOException;
import java.util.List;

public interface ActionService {
    FullInfoLot getEmployeeFullLotById(int id);
    void startLot(int id);
    void placeABet(int id, String bidderName);
    void stopLot(int id);
    Lot createLot(String title, String description, int startPrice, int bidPrice);
    List<Lot> getLotsByStatusAndPage(Status status, int page);
    String getFrequentBidder(int id);
    Bet getFirstBidder(int id);
    String exportLots() throws IOException;
}
