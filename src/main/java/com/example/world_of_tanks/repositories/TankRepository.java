package com.example.world_of_tanks.repositories;

import com.example.world_of_tanks.models.Tank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TankRepository extends JpaRepository<Tank, Long> {

    Optional<Tank> findByName(String name);
}
