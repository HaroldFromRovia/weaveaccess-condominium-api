package com.waveaccess.condominiumapi.controllers.moderator;

import com.waveaccess.condominiumapi.controllers.client.AuthController;
import com.waveaccess.condominiumapi.dto.UserDto;
import com.waveaccess.condominiumapi.mappers.UserMapper;
import com.waveaccess.condominiumapi.security.annotations.HasRoleModerator;
import com.waveaccess.condominiumapi.security.annotations.PermitAll;
import com.waveaccess.condominiumapi.services.interfaces.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@HasRoleModerator
@Api("Moderating")
@RestController
@RequiredArgsConstructor
@RequestMapping(ClientModeController.ROOT_URL)
public class ClientModeController {

    public static final String ROOT_URL = "/v1/moder/";
    public static final String PAGE_URL = "/page";

    private final UserService userService;
    private final UserMapper userMapper;

    @ApiOperation("Get inactive users")
    @ApiResponses({
            @ApiResponse(
                    code = SC_BAD_REQUEST,
                    message = "Account or password not specified: \"empty-param\"; " +
                            "account param has invalid email: \"invalid-email\""),
            @ApiResponse(
                    code = SC_UNAUTHORIZED,
                    message = "Authentication failed"
            )
    })
    @GetMapping(PAGE_URL)
    public Page<UserDto> getInactivePage(Pageable pageable) {
        return userService.getPage(pageable).map(userMapper::userToUserDto);
    }
}
