package com.example.lotwork.controller;

import com.example.lotwork.DTO.BetDto;
import com.example.lotwork.service.ActionService;
import io.swagger.v3.oas.annotations.Operation;
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
    public BetDto infoFirstBet(@PathVariable Integer id) {
        return actionService.getFirstBidder(id);
    }
    @GetMapping("{id}/frequent")
    @Operation(summary = "Возвращает имя ставившего на данный лот наибольшее количество раз",
            description = "Наибольшее колличество вычисляется из общего количества ставок на лот")
    public String nameBedFrequentLot(@PathVariable Integer id) {
        return actionService.getFrequentBidder(id);
    }
}
