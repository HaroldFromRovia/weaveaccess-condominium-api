package com.waveaccess.condominiumapi.controllers.client;

import com.waveaccess.condominiumapi.dto.ResourceDto;
import com.waveaccess.condominiumapi.mappers.ResourceMapper;
import com.waveaccess.condominiumapi.models.Resource;
import com.waveaccess.condominiumapi.services.interfaces.ResourceService;
import com.waveaccess.condominiumapi.utils.DateTimeUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api
@RestController
@RequiredArgsConstructor
@RequestMapping(ResourceController.ROOT_URL)
@PreAuthorize("isAuthenticated()")
public class ResourceController {

    public static final String ROOT_URL = "/v1";
    public static final String GET_PAGE_URL = "/resources";
    public static final String GET_OBJECTS_URL = "/objects";
    public static final String GET_PAGE_AVAILABLE_BETWEEN = "/resourcesAvailable";

    private final ResourceService resourceService;
    private final ResourceMapper resourceMapper;

    @GetMapping(GET_PAGE_URL)
    public Page<ResourceDto> getPage(Pageable pageable) {
        return resourceService.getResources(pageable).map(resourceMapper::resourceToDto);
    }

    @GetMapping(GET_OBJECTS_URL)
    public Page<ResourceDto> getObjects(Pageable pageable) {
        return resourceService.getObjects(pageable).map(resourceMapper::resourceToDto);
    }

    @GetMapping(GET_PAGE_AVAILABLE_BETWEEN)
    public Map<Long, List<String>> getAvailableForDate(
            @RequestParam Date date,
            Pageable pageable) {
        return resourceService.getAvailableForDate(date, pageable);
    }
}
