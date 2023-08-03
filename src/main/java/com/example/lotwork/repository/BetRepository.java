package com.example.lotwork.repository;

import com.example.lotwork.controller.BetsController;
import com.example.lotwork.model.Bet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BetRepository extends CrudRepository<Bet, Integer> {

    @Query("SELECT new com.example.lotwork.controller.BetsController" +
            "(b.nameBidder,MAX(b.bidDate))FROM Bid b WHERE b.lot.id=:id" +
            " GROUP BY nameBidder ORDER BY COUNT(b.lot.id) DESC, LIMIT 1")
    abstract Optional<BetsController> nameBedFrequentLot(@Param("id") int id);



}
