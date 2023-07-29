package com.example.lotwork.service;

import com.example.lotwork.DTO.BetDto;
import com.example.lotwork.DTO.FullInfoLot;
import com.example.lotwork.DTO.LotDto;

import java.io.IOException;
import java.util.List;

public interface ActionService {
    FullInfoLot getEmployeeFullLotById(int id);
    void startLot(int id);
    void placeABet(int id, String bidderName);
    void stopLot(int id);
    LotDto createLot(String title, String description, int startPrice, int bidPrice);
    List<LotDto> getLotsByStatusAndPage(Integer status, int page);
    String getFrequentBidder(int id);
    BetDto getFirstBidder(int id);


    String exportLots() throws IOException;


}
