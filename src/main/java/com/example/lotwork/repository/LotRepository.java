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

    @Query(value = "select l.lot_id, l.title, l.bid_price , l.start_price , l.description , l.status , " +
            "(select b.bidder_name from public.bid b where b.lot_id = l.lot_id and b.bid_date = " +
            "(select max(b.bid_date)from public.bid b where b.lot_id = l.lot_id )group by b.bidder_name) bidder_name, " +
            "(select max(b.bid_date)from public.bid b where b.lot_id = l.lot_id ) bid_date, " +
            "(select count(b.id)*l.bid_price+l.start_price from public.bid b where b.lot_id = l.lot_id) currentPrice " +
            "from lot l inner join bid b \n" +
            "on l.lot_id = b.lot_id group by l.title, l.bid_price , l.start_price , l.description , l.status , l.lot_id " +
            "having  l.lot_id = ?1",
            nativeQuery = true)
    FullInfoLot getFullInfoLot(int id);



    Iterable<FullInfoLot> getFullLots();
}
