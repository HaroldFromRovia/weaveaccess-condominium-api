package com.waveaccess.condominiumapi.security;

import com.waveaccess.condominiumapi.models.User;
import com.waveaccess.condominiumapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(email == null || email.equals("")) {
            throw new UsernameNotFoundException("Login is empty.");
        }
        if(!email.trim().equals(email)) {
            throw new UsernameNotFoundException("Unacceptable login format.");
        }
        Optional<User> userOptional = userRepository.findByEmailIgnoreCase(email);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDetailsImpl(user);
        }
        throw new UsernameNotFoundException("User not found.");
    }
}
