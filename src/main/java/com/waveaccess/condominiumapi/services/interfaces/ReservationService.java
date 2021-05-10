package com.waveaccess.condominiumapi.services.interfaces;

import com.waveaccess.condominiumapi.dto.ReservationDto;
import com.waveaccess.condominiumapi.dto.forms.ReservationForm;
import com.waveaccess.condominiumapi.models.Reservation;
import com.waveaccess.condominiumapi.models.Resource;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalTime;

public interface ReservationService {
    ReservationDto reserve(ReservationForm reservation, UserDetails userDetails, LocalTime startTime, LocalTime endTime);

    Boolean isAvailable(Resource resource, LocalTime startTime, LocalTime endTime);
}
