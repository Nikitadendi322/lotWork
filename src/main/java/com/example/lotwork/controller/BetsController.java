package com.example.lotwork.controller;

import com.example.lotwork.model.Bet;
import com.example.lotwork.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bet")
@RequiredArgsConstructor
public class BetsController {
    private final ActionService actionService;
    @GetMapping("{id}first")
    public Bet infoFirstBet(@PathVariable Integer id) {
        return actionService.getFirstBidder(id);
    }
    @GetMapping("{id}frequent")
    public String nameBedFrequentLot(@PathVariable Integer id) {
        return actionService.getFrequentBidder(id);
    }
}
