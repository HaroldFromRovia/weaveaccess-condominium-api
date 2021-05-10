package com.waveaccess.condominiumapi.repositories;

import com.waveaccess.condominiumapi.models.Resource;
import com.waveaccess.condominiumapi.models.enums.Classification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ResourceRepository extends PagingAndSortingRepository<Resource, Long> {

    Page<Resource> findAllByClassification(Classification classification, Pageable pageable);

    @Query("SELECT resource FROM Resource resource LEFT JOIN  resource.reservation reservation " +
            "where reservation.reservationDate = :date")
    Page<Resource> findAllByReservationDate(@Param("date") Date date, Pageable pageable);
}
