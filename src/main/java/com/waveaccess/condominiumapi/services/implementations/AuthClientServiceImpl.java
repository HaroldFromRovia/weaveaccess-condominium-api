package com.waveaccess.condominiumapi.services.implementations;

import com.waveaccess.condominiumapi.dto.forms.LoginForm;
import com.waveaccess.condominiumapi.dto.forms.SignUpForm;
import com.waveaccess.condominiumapi.mappers.UserMapper;
import com.waveaccess.condominiumapi.models.enums.Role;
import com.waveaccess.condominiumapi.models.User;
import com.waveaccess.condominiumapi.repositories.UserRepository;
import com.waveaccess.condominiumapi.services.interfaces.AuthClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthClientServiceImpl implements AuthClientService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public User signUp(SignUpForm signUpForm) {

        if (userRepository.findByEmail(signUpForm.getEmail()).isPresent())
            throw new BadCredentialsException("user-exists");

        var user = userMapper.formToUser(signUpForm);
        user.setRole(Role.CLIENT);
        user.setIsEnabled(false);

        return userRepository.save(user);
    }

    @Override
    public User login(LoginForm loginForm) {

        //TODO User token authorization
        return null;
    }
}
