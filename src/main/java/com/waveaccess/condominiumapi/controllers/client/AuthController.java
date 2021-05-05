package com.waveaccess.condominiumapi.controllers.client;

import com.waveaccess.condominiumapi.dto.UserDto;
import com.waveaccess.condominiumapi.dto.forms.LoginForm;
import com.waveaccess.condominiumapi.dto.forms.SignUpForm;
import com.waveaccess.condominiumapi.mappers.UserMapper;
import com.waveaccess.condominiumapi.security.annotations.PermitAll;
import com.waveaccess.condominiumapi.services.interfaces.AuthClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@PermitAll
@Api("Authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping(AuthController.ROOT_URL)
public class AuthController {

    public static final String ROOT_URL = "/v1/client";
    public static final String REGISTER_URL = "/register";
    public static final String LOGIN_URL = "/login";

    private final AuthClientService authService;
    private final UserMapper mapper;

    @ApiOperation("Sign up")
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
    @PostMapping(REGISTER_URL)
    public UserDto register(SignUpForm form) {
        return mapper.userToUserDto(authService.signUp(form));
    }

    @ApiOperation("Login")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "",
                    response = Authentication.class),
            @ApiResponse(
                    code = 405,
                    message = "User is not enabled",
                    response = Authentication.class)})
    @PostMapping(LOGIN_URL)
    public UserDto login(LoginForm form) {
        throw new IllegalStateException("Method never called");
    }

}
