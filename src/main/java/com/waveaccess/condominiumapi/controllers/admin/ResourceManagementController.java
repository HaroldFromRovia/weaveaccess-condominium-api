package com.waveaccess.condominiumapi.controllers.admin;

import com.waveaccess.condominiumapi.dto.ResourceDto;
import com.waveaccess.condominiumapi.dto.forms.ResourceForm;
import com.waveaccess.condominiumapi.mappers.ResourceMapper;
import com.waveaccess.condominiumapi.services.interfaces.ResourceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api("Resource managing controller")
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(ResourceManagementController.ROOT_URL)
public class ResourceManagementController {

    public static final String ROOT_URL = "/v1/resource";
    public static final String MANAGEMENT_URL = "/manage/{id}";

    private final ResourceService resourceService;
    private final ResourceMapper resourceMapper;

    @PutMapping(MANAGEMENT_URL)
    public ResourceDto manageResource(ResourceForm resourceFrom, @PathVariable Long id) {
        return resourceMapper.resourceToDto(resourceService.editResource(resourceFrom, id));
    }


}
