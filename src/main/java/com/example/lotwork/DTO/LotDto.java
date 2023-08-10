package com.example.lotwork.DTO;

import com.example.lotwork.model.Lot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotDto {

    private int startPrice;
    private int bidPrice;
    private String title;
    private Integer id;
    private int statusId;
    private String description;

    public static LotDto fromLot(Lot lot){
        LotDto lotDto=new LotDto();
        lotDto.setBidPrice(lot.getBidPrice());
        lotDto.setStartPrice(lot.getStartPrice());
        lotDto.setTitle(lotDto.getTitle());
        lotDto.setId(lotDto.getId());
        lotDto.setStatusId(lotDto.getStatusId());
        lotDto.setDescription(lotDto.getDescription());
        return lotDto;
    }

}
