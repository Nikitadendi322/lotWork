package com.example.lotwork.controller;

import com.example.lotwork.DTO.FullInfoLot;
import com.example.lotwork.DTO.LotDto;
import com.example.lotwork.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lot")
@RequiredArgsConstructor
public class LotsController {

    private final ActionService actionService;


    @GetMapping("{id}")
    public FullInfoLot fullInfoLot(@PathVariable Integer id) {
        return actionService.getEmployeeFullLotById(id);
    }

    @PostMapping("{id}/start")
    public void tradeLot(@PathVariable Integer id) {
        actionService.startLot(id);

    }

    @PostMapping("{id}/bit")
    public void betLot(@PathVariable int id,
                       @RequestParam("bidderName") String bidderName) {
        actionService.placeABet(id, bidderName);
    }

    @PostMapping("{id}/stop")
    public void stopTradeLot(@PathVariable Integer id) {
        actionService.stopLot(id);

    }

    @PostMapping
    public LotDto createLot(@RequestParam String title,
                            @RequestParam String description,
                            @RequestParam int startPrice,
                            @RequestParam int bidPrice) {
        return actionService.createLot(title, description, startPrice, bidPrice);
    }

    @GetMapping()
    public List<LotDto> fullLots(@RequestParam("status") Integer status,
                              @RequestParam("page") int page) {
        return actionService.getLotsByStatusAndPage(status, page);
    }

    @GetMapping("/export")
    public int exportLots() throws IOException {
        return actionService.exportLots();
    }

}
