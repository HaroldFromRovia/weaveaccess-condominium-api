package com.waveaccess.condominiumapi.services.implementations;

import com.waveaccess.condominiumapi.dto.ReservationDto;
import com.waveaccess.condominiumapi.dto.forms.ReservationForm;
import com.waveaccess.condominiumapi.exceptions.ReservationWrongTimeException;
import com.waveaccess.condominiumapi.exceptions.ResourceNotAvailableException;
import com.waveaccess.condominiumapi.mappers.ReservationMapper;
import com.waveaccess.condominiumapi.models.Reservation;
import com.waveaccess.condominiumapi.models.Resource;
import com.waveaccess.condominiumapi.repositories.ReservationRepository;
import com.waveaccess.condominiumapi.repositories.ResourceRepository;
import com.waveaccess.condominiumapi.repositories.UserRepository;
import com.waveaccess.condominiumapi.services.interfaces.ReservationService;
import com.waveaccess.condominiumapi.utils.TimeComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ResourceRepository resourceRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ReservationDto reserve(ReservationForm reservationForm, UserDetails userDetails,
                                  LocalTime startTime, LocalTime endTime) {

        reservationForm.setStartTime(startTime);
        reservationForm.setEndTime(endTime);
        var resource = resourceRepository.findById(reservationForm.getResourceId())
                .orElseThrow(ResourceNotFoundException::new);

        if (!isAvailable(resource, startTime, endTime))
            throw new ResourceNotAvailableException("Another user have reservation at provided time");

        var reservation = reservationMapper.formToReservation(reservationForm);
        var user = userRepository.findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(IllegalArgumentException::new);

        reservation.setUser(user);
        reservation.setResource(resource);

        return reservationMapper.reservationToDto(reservationRepository.save(reservation));
    }

    @Override
    public Boolean isAvailable(Resource resource, LocalTime startTime, LocalTime endTime) {

        Comparator<Reservation> comparator = new TimeComparator();
        var reservations = resource.getReservation();
        reservations.sort(comparator);
        if (startTime.isBefore(resource.getStartTime()) || endTime.isAfter(resource.getEndTime())) {
            throw new ReservationWrongTimeException("Reservation time should be in resource working time");
        }
        if (reservations.isEmpty()) return true;

        for (Reservation reservation : reservations) {
            if (!(startTime.compareTo(reservation.getStartTime()) >= 0 && endTime.compareTo(reservation.getEndTime()) <= 0))
                return true;
        }
        return false;
    }
}
