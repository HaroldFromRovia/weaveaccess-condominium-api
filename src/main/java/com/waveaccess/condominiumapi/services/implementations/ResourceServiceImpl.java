package com.waveaccess.condominiumapi.services.implementations;

import com.waveaccess.condominiumapi.dto.forms.ResourceForm;
import com.waveaccess.condominiumapi.mappers.ResourceMapper;
import com.waveaccess.condominiumapi.models.Reservation;
import com.waveaccess.condominiumapi.models.Resource;
import com.waveaccess.condominiumapi.models.enums.Classification;
import com.waveaccess.condominiumapi.repositories.ResourceRepository;
import com.waveaccess.condominiumapi.services.interfaces.FileService;
import com.waveaccess.condominiumapi.services.interfaces.ResourceService;
import com.waveaccess.condominiumapi.utils.TimeComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {


    private static final Integer GAP_IN_SECONDS = 300;
    private final ResourceRepository resourceRepository;
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
    public Page<Map<Resource, String>> getAvailableResourcesBetween(Instant startTime, Instant endTime, Pageable pageable) {

        Page<Resource> resources = resourceRepository.findAllByStartTimeBetweenAndEndTimeBetween(startTime,
                endTime, startTime, endTime, pageable);
        if (resources.isEmpty()) return null;

        List<Map<Resource, String>> availableResources = new ArrayList<>();
        for (Resource resource : resources.getContent()) {
            availableResources.add(getAvailableTimeForResource(resource, startTime, endTime));
        }

        int start = (int) pageable.getOffset();
        int end = (Math.min((start + pageable.getPageSize()), availableResources.size()));

        return new PageImpl<>(availableResources.subList(start, end), pageable, availableResources.size());
    }

    @Override
    public Map<Resource, String> getAvailableTimeForResource(Resource resource, Instant startTime, Instant endTime) {

        Map<Resource, String> resultMap = new HashMap<>();
        Instant startLastReview = endTime;
        Instant endLastReview = startTime;
        Comparator<Reservation> comparator = new TimeComparator();
        resource.getReservation().sort(comparator);

        for (Reservation reservation : resource.getReservation()) {
            if (reservation.getStartTime().isAfter(endLastReview) &&
                    reservation.getEndTime().isBefore(startLastReview) &&
                    reservation.getEndTime().minusSeconds(
                            reservation.getStartTime().getEpochSecond()
                    ).getEpochSecond() > GAP_IN_SECONDS) {

                resultMap.put(resource, reservation.getStartTime().toString() + " - " + reservation.getEndTime());
                startLastReview = reservation.getStartTime();
                endLastReview = reservation.getEndTime();
            }
        }

        return resultMap;
    }
}
