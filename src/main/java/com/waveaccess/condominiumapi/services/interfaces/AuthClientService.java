package com.waveaccess.condominiumapi.services.interfaces;

import com.waveaccess.condominiumapi.dto.UserDto;
import com.waveaccess.condominiumapi.dto.forms.LoginForm;
import com.waveaccess.condominiumapi.dto.forms.SignUpForm;
import com.waveaccess.condominiumapi.models.User;
import org.springframework.stereotype.Service;

public interface AuthClientService {

    User signUp(SignUpForm signUpForm);
    User login(LoginForm loginForm);

}
