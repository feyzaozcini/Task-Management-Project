package com.turkcell.userservice.services.concretes;

import com.turkcell.userservice.entities.User;
import com.turkcell.userservice.repositories.UserRepository;
import com.turkcell.userservice.services.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Spring Security
        return userRepository.findByEmail(username).orElseThrow(() -> new BadCredentialsException(""));
    }
}