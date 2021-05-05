package com.waveaccess.condominiumapi.services.implementations;

import com.waveaccess.condominiumapi.dto.UserDto;
import com.waveaccess.condominiumapi.models.User;
import com.waveaccess.condominiumapi.repositories.UserRepository;
import com.waveaccess.condominiumapi.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAllByIsEnabled(pageable, false);
    }

    @Override
    public User setActive(Long id) {
        var user = findById(id);
        user.setIsEnabled(true);

        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        Optional<User> bookCandidate = userRepository.findById(id);
        return bookCandidate.orElseThrow(ResourceNotFoundException::new);
    }
}
