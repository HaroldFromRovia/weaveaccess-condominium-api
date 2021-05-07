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

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping(ResourceController.ROOT_URL)
@PreAuthorize("isAuthenticated()")
public class ReservationController {

    public static final String ROOT_URL = "/v1/reservation";
    public static final String RESERVATION_URL = "/reserve/{resourceId}";
    private final ReservationService reservationService;

    @PutMapping(RESERVATION_URL)
    public ReservationDto reserve(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long resourceId,
                                  ReservationForm reservationForm) {
        return reservationService.reserve(resourceId, reservationForm, userDetails);
    }

}
