package com.waveaccess.condominiumapi.repositories;

import com.waveaccess.condominiumapi.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {


}
