package com.waveaccess.condominiumapi.controllers.admin;

import com.waveaccess.condominiumapi.dto.ResourceDto;
import com.waveaccess.condominiumapi.dto.forms.ResourceForm;
import com.waveaccess.condominiumapi.mappers.ResourceMapper;
import com.waveaccess.condominiumapi.services.interfaces.ResourceService;
import com.waveaccess.condominiumapi.utils.DateTimeUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Api("Resource managing controller")
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(ResourceManagementController.ROOT_URL)
public class ResourceManagementController {

    public static final String ROOT_URL = "/v1/resource";
    public static final String MANAGEMENT_URL = "/manage/{id}";
    public static final String CREATE_RESOURCE_URL = "/create";

    private final ResourceService resourceService;
    private final ResourceMapper resourceMapper;

    @RequestMapping(path = MANAGEMENT_URL, method = RequestMethod.POST,
            consumes = {"multipart/form-data"}, produces = "application/json")
    public ResourceDto manage(ResourceForm resourceForm, @PathVariable Long id,
                              @RequestParam LocalTime startTime, @RequestParam LocalTime endTime) {
        resourceForm.setStartTime(startTime);
        resourceForm.setEndTime(endTime);

        return resourceMapper.resourceToDto(resourceService.editResource(resourceForm, id));
    }

    @RequestMapping(path = CREATE_RESOURCE_URL, method = RequestMethod.POST,
            consumes = {"multipart/form-data"}, produces = "application/json")
    public ResourceDto create(ResourceForm resourceForm,
                              @RequestParam LocalTime startTime, @RequestParam LocalTime endTime) {
        resourceForm.setStartTime(startTime);
        resourceForm.setEndTime(endTime);

        return resourceMapper.resourceToDto(resourceService.saveResource(resourceForm));
    }

}
