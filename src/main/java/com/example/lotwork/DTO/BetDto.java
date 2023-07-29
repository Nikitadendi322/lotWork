package com.example.lotwork.DTO;

import com.example.lotwork.model.Bet;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BetDto {

    private String nameBidder;
    private LocalDateTime bidDate;

    public static BetDto fromBet(Bet bet){
        BetDto betDto=new BetDto();
        betDto.setNameBidder(bet.getNameBidder());
        betDto.setBidDate(bet.getBidDate());
        return betDto;
    }

}
