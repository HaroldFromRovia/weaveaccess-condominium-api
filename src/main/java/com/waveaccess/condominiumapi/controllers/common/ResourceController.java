package com.waveaccess.condominiumapi.controllers.common;

import com.waveaccess.condominiumapi.dto.ResourceDto;
import com.waveaccess.condominiumapi.mappers.ResourceMapper;
import com.waveaccess.condominiumapi.services.interfaces.ResourceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ResourceController {

    public static final String GET_PAGE_URL = "/resources";

    private final ResourceService resourceService;
    private final ResourceMapper resourceMapper;

    @GetMapping(GET_PAGE_URL)
    public Page<ResourceDto> getResourceList(Pageable pageable) {
        return resourceService.getResources(pageable).map(resourceMapper::resourceToDto);
    }

}
