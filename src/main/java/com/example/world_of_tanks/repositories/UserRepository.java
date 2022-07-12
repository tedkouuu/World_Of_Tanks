package com.example.world_of_tanks.repositories;

import com.example.world_of_tanks.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String userName);

    Optional<UserEntity> findByEmail(String email);
}
