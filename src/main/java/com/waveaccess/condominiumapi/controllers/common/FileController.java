package com.waveaccess.condominiumapi.controllers.common;

import com.waveaccess.condominiumapi.services.interfaces.FileService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

@Api
@RestController
@RequiredArgsConstructor
@MultipartConfig
@PreAuthorize("isAuthenticated()")
public class FileController {

    private final FileService fileService;

    @GetMapping(value = "/files/{uuid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    Resource getFile(@PathVariable String uuid, HttpServletResponse response) {
        var resource = fileService.download(uuid);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"");
        return resource;
    }

}
