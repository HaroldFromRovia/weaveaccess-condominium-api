package com.waveaccess.condominiumapi.services.interfaces;

import com.waveaccess.condominiumapi.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> getPage(Pageable pageable);

}
