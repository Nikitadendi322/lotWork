package com.example.lotwork.repository;

import com.example.lotwork.DTO.LotDto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaginLotRepository extends PagingAndSortingRepository<LotDto, Integer> {
}
