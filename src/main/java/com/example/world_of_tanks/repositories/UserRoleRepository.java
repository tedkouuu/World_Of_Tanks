package com.example.world_of_tanks.repositories;

import com.example.world_of_tanks.models.UserEntity;
import com.example.world_of_tanks.models.UserRoleEntity;
import com.example.world_of_tanks.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByUserRole(UserRoleEnum userRoleEnum);

}
