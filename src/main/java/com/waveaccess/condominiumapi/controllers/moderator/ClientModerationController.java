package com.waveaccess.condominiumapi.controllers.moderator;

import com.waveaccess.condominiumapi.dto.UserDto;
import com.waveaccess.condominiumapi.mappers.UserMapper;
import com.waveaccess.condominiumapi.models.User;
import com.waveaccess.condominiumapi.services.interfaces.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Moderating")
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
@RequestMapping(ClientModerationController.ROOT_URL)
public class ClientModerationController {

    public static final String ROOT_URL = "/v1/moder/";
    public static final String PAGE_URL = "/page";

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(PAGE_URL)
    public Page<UserDto> getInactivePage(Pageable pageable) {
        return userService.getPage(pageable).map(userMapper::userToDto);
    }

    @PostMapping
    public User setAsActive(Long id) {
        return userService.setActive(id);
    }
}
