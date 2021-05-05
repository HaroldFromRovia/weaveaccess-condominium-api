package com.waveaccess.condominiumapi.services.interfaces;

import java.util.Optional;

public interface TokenService {

    Optional<Long> getUserId(String authToken);
    String generateToken(Long userId);
}
