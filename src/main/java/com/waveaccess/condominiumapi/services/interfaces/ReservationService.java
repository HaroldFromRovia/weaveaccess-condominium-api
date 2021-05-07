package com.waveaccess.condominiumapi.services.interfaces;

import com.waveaccess.condominiumapi.dto.ReservationDto;
import com.waveaccess.condominiumapi.dto.forms.ReservationForm;
import com.waveaccess.condominiumapi.models.Reservation;
import com.waveaccess.condominiumapi.models.Resource;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;

public interface ReservationService {
    ReservationDto reserve(Long resourceId, ReservationForm reservation, UserDetails userDetails);
}
