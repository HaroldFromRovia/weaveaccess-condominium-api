package com.waveaccess.condominiumapi.services.implementations;

import com.waveaccess.condominiumapi.dto.forms.LoginForm;
import com.waveaccess.condominiumapi.dto.forms.SignUpForm;
import com.waveaccess.condominiumapi.mappers.UserMapper;
import com.waveaccess.condominiumapi.models.enums.Role;
import com.waveaccess.condominiumapi.models.User;
import com.waveaccess.condominiumapi.repositories.UserRepository;
import com.waveaccess.condominiumapi.services.interfaces.AuthClientService;
import com.waveaccess.condominiumapi.services.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthClientServiceImpl implements AuthClientService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FileService fileService;


    @Override
    @Transactional
    public User signUp(SignUpForm signUpForm) {

        if (userRepository.findByEmailIgnoreCase(signUpForm.getEmail()).isPresent())
            //TODO validation
            throw new IllegalArgumentException("user already exists");

        var imagePath = fileService.save(signUpForm.getImage());
        var user = userMapper.formToUser(signUpForm);

        user.setRole(Role.CLIENT);
        user.setIsEnabled(false);
        user.setImagePath(imagePath);

        return userRepository.save(user);
    }

    @Override
    public User login(LoginForm loginForm) {

        //TODO User token authentication
        return null;
    }
}
