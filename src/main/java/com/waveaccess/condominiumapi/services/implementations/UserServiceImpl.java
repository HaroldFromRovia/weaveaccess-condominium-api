package com.waveaccess.condominiumapi.services.implementations;

import com.waveaccess.condominiumapi.models.User;
import com.waveaccess.condominiumapi.repositories.UserRepository;
import com.waveaccess.condominiumapi.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAllByIsEnabled(pageable, false);
    }
}
