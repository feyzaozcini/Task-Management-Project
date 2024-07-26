package com.turkcell.userservice.repositories;

import com.turkcell.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
