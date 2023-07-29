package com.example.lotwork.repository;

import com.example.lotwork.DTO.LotDto;
import com.example.lotwork.model.Lot;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaginLotRepository extends PagingAndSortingRepository<LotDto, Integer> {
}
