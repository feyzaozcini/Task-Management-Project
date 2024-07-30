package com.turkcell.userservice.services.rules;


import com.turkcell.userservice.core.utils.types.BusinessException;
import com.turkcell.userservice.repositories.UserRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository userRepository;

    public void checkIfUserAlreadyExists(String email) {
        if (userRepository.existsByEmail(email)){
            throw new BusinessException("This email address already exists.");
        }
    }


    public void checkIfUserNotExists(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new BusinessException("User not found!");
        }
    }

    public void checkIfUserExistsById(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User Not Found!");
        }
    }
}