package com.example.lotwork.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_bidder;
    @Column(name = "name_bidder")
    private String nameBidder;
    @Column(name = "bid_date")
    private LocalDateTime bidDate;

    public Bet(String nameBidder) {
        this.nameBidder = nameBidder;
        this.bidDate = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalDateTime.now().toLocalTime());
    }

}
