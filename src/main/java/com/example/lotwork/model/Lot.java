package com.example.lotwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Min(3)
    @Max(64)
    private String title;
    @Min(1)
    @Max(4096)
    private String description;
    @Min(1)
    @Column(name = "start_price")
    private int startPrice;
    @Min(1)
    @Column(name = "bid_price")
    private int bidPrice;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bets")
    private List<Bet> bets;

    public Lot(Integer id, int statusId, String title, String description, int startPrice, int bidPrice) {
        this.id = id;
        this.status = getStatus(statusId);
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.bidPrice = bidPrice;
    }
    private Status getStatus(int statusId) {
        Status status;
        if(statusId == 1){
            status = Status.STARTED;
        }else if(statusId == 2){
            status = Status.STOPPED;
        }else{
            status = Status.CREATED;
        }
        return status;
    }

}
