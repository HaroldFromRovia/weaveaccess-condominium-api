package com.waveaccess.condominiumapi.controllers.client;

import com.waveaccess.condominiumapi.dto.ReservationDto;
import com.waveaccess.condominiumapi.dto.forms.ReservationForm;
import com.waveaccess.condominiumapi.models.Reservation;
import com.waveaccess.condominiumapi.services.interfaces.ReservationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping(ReservationController.ROOT_URL)
@PreAuthorize("isAuthenticated()")
public class ReservationController {

    public static final String ROOT_URL = "/v1/reservation";
    public static final String RESERVATION_URL = "/reserve";
    private final ReservationService reservationService;

    @PutMapping(RESERVATION_URL)
    public ReservationDto reserve(@AuthenticationPrincipal UserDetails userDetails,
                                  ReservationForm reservationForm,
                                  @RequestParam LocalTime startTime, @RequestParam LocalTime endTime) {
        return reservationService.reserve(reservationForm, userDetails, startTime, endTime);
    }

}
