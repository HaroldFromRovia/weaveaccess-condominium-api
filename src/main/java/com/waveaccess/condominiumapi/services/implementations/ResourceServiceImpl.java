package com.waveaccess.condominiumapi.services.implementations;

import com.waveaccess.condominiumapi.dto.forms.ResourceForm;
import com.waveaccess.condominiumapi.mappers.ResourceMapper;
import com.waveaccess.condominiumapi.models.Reservation;
import com.waveaccess.condominiumapi.models.Resource;
import com.waveaccess.condominiumapi.models.enums.Classification;
import com.waveaccess.condominiumapi.repositories.ReservationRepository;
import com.waveaccess.condominiumapi.repositories.ResourceRepository;
import com.waveaccess.condominiumapi.services.interfaces.FileService;
import com.waveaccess.condominiumapi.services.interfaces.ResourceService;
import com.waveaccess.condominiumapi.utils.TimeComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final static int GAP_IN_SECONDS = 300;
    private final ResourceRepository resourceRepository;
    private final ReservationRepository reservationRepository;
    private final ResourceMapper resourceMapper;
    private final FileService fileService;

    @Override
    public Page<Resource> getResources(Pageable pageable) {
        return resourceRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Resource saveResource(ResourceForm resourceForm) {
        var imagePath = fileService.save(resourceForm.getImage());
        var resource = resourceMapper.formToResource(resourceForm);

        resource.setImagePath(imagePath);
        return resourceRepository.save(resource);
    }

    @Override
    @Transactional
    public Resource editResource(ResourceForm resourceForm, Long id) {
        Resource resource = findById(id);
        String imagePath = resource.getImagePath();

        if (resourceForm.getImage() != null) {
            imagePath = fileService.save(resourceForm.getImage());
        }
        resourceMapper.updateResourceFromDto(resourceForm, resource);
        resource.setImagePath(imagePath);

        return resourceRepository.save(resource);
    }

    @Override
    public Resource findById(Long id) {
        Optional<Resource> resourceOptional = resourceRepository.findById(id);
        return resourceOptional.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Page<Resource> getObjects(Pageable pageable) {
        return resourceRepository.findAllByClassification(Classification.OBJECT, pageable);
    }

    @Override
    public Map<Long, List<String>> getAvailableForDate(Date date, Pageable pageable) {

        Map<Long, List<String>> availableBetween = new HashMap<>();
        List<Reservation> reservations = reservationRepository.findAllByReservationDate(date, pageable).getContent();
        var resources = resourceRepository.findAllByReservationDate(date, pageable);

        if (reservations.isEmpty()) {
            for (Resource resource : resources) {
                availableBetween.put(resource.getId(), List.of(resource.getStartTime().toString() +
                        "-" + resource.getEndTime().toString()));
            }
            return availableBetween;
        }

        //Getting reservations for resources, and calculating available intervals
        for (Resource resource : resources) {
            List<String> timesList = new ArrayList<>();
            var sorted = resource.getReservation().stream()
                    .sorted(new TimeComparator())
                    .collect(Collectors.toList());
            LocalTime startTime = resource.getStartTime();
            LocalTime endTime = resource.getEndTime();
            if (sorted.get(0).getStartTime().compareTo(startTime) > 0)
                timesList.add(startTime + "-" + sorted.get(0).getStartTime());


            for (Reservation reservation : sorted) {
                if (Duration.between(startTime, reservation.getStartTime()).getSeconds() > 0 &&
                        Duration.between(startTime, reservation.getStartTime()).getSeconds() > 0) {
                    timesList.add(startTime + "-" + endTime);
                }
                startTime = reservation.getStartTime();
                endTime = reservation.getEndTime();
            }
            availableBetween.put(resource.getId(), timesList);

            if (sorted.get(sorted.size() - 1).getEndTime().compareTo(endTime) < 0)
                timesList.add(endTime + "-" + sorted.get(sorted.size() - 1).getEndTime());
        }

        return availableBetween;
    }

}
