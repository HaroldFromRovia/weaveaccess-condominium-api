package com.waveaccess.condominiumapi.services.implementations;

import com.waveaccess.condominiumapi.dto.ReservationDto;
import com.waveaccess.condominiumapi.dto.forms.ReservationForm;
import com.waveaccess.condominiumapi.exceptions.ResourceNotAvailableException;
import com.waveaccess.condominiumapi.repositories.ReservationRepository;
import com.waveaccess.condominiumapi.repositories.ResourceRepository;
import com.waveaccess.condominiumapi.services.interfaces.ReservationService;
import com.waveaccess.condominiumapi.services.interfaces.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ResourceRepository resourceRepository;
    private final ResourceService resourceService;
    private final ReservationRepository reservationRepository;
    private final

    @Override
    public ReservationDto reserve(Long resourceId, ReservationForm reservation, UserDetails userDetails) {

        Instant startTime = reservation.getStartTime();
        Instant endTime = reservation.getEndTime();
        var resourcesInRange = resourceRepository
                .findAllByStartTimeBetweenAndEndTimeBetween(startTime, endTime,
                        startTime, endTime, Pageable.unpaged());
        var resource = resourceRepository.findById(resourceId).orElseThrow(ResourceNotFoundException::new);

        if (resourcesInRange.isEmpty()) {
            throw new ResourceNotAvailableException("Resource is not available in range: " + startTime + " - " + endTime);
        }
        if (resourceService.getAvailableTimeForResource(resource, startTime, endTime).isEmpty()) {
            throw new ResourceNotAvailableException("Another user have reservation for this time");
        }


        reservationRepository.save();
        return null;
    }
}
