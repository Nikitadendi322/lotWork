package com.example.lotwork.DTO;

import com.example.lotwork.model.Lot;
import com.example.lotwork.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLot {

    private String title;
    private String description;
    private int startPrice;
    private int bidPrice;


    public static CreateLot fromLot(Lot lot){
        CreateLot createLot = new CreateLot();
        createLot.setTitle(lot.getTitle());
        createLot.setDescription(lot.getDescription());
        createLot.setStartPrice(lot.getStartPrice());
        createLot.setBidPrice(lot.getBidPrice());
        return createLot;
    }

    public Lot toLot(CreateLot createLot){
        Lot lot = new Lot();
        lot.setStatus(Status.CREATED);
        lot.setTitle(this.getTitle());
        lot.setDescription(this.getDescription());
        lot.setStartPrice(this.getStartPrice());
        lot.setBidPrice(this.getBidPrice());
        return lot;
    }
}
