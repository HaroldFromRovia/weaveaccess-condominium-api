package com.waveaccess.condominiumapi.repositories;

import com.waveaccess.condominiumapi.models.Resource;
import com.waveaccess.condominiumapi.models.enums.Classification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalTime;

@Repository
public interface ResourceRepository extends PagingAndSortingRepository<Resource, Long> {

    Page<Resource> findAllByClassification(Classification classification, Pageable pageable);

    Page<Resource> findAllByStartTimeBetweenAndEndTimeBetween(Instant startTime, Instant startTime2, Instant endTime, Instant endTime2, Pageable pageable);
}
