package com.waveaccess.condominiumapi.repositories;

import com.waveaccess.condominiumapi.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Page<User> findAllByIsEnabled(Pageable pageable, Boolean isEnabled);

}
