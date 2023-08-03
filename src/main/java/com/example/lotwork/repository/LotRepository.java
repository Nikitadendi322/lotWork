package com.example.lotwork.repository;

import com.example.lotwork.DTO.FullInfoLot;
import com.example.lotwork.model.Lot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LotRepository extends CrudRepository<Lot, Integer> {

    @Query(value = "SELECT new com.example.action." +
            "FullInfoLot(e.name, e.price, e.state)" +
            "FROM Lot e " +
            "WHERE id:=id",
            nativeQuery = true)
    List<Lot> getLotById(@Param("id") int id);


    Iterable<FullInfoLot> getFullLots();
}
