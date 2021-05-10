package com.waveaccess.condominiumapi.repositories;

import com.waveaccess.condominiumapi.models.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

    List<Reservation> findAllByReservationDateAndResourceId(Date date, Long resourceId);

    Page<Reservation> findAllByReservationDate(Date date, Pageable pageable);
}
