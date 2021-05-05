package com.waveaccess.condominiumapi.services.interfaces;

import com.waveaccess.condominiumapi.dto.UserDto;
import com.waveaccess.condominiumapi.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Page<User> getPage(Pageable pageable);

    User setActive(Long id);

    User findById(Long id);

}
