package com.example.world_of_tanks.services;

import com.example.world_of_tanks.models.UserEntity;
import com.example.world_of_tanks.models.UserRoleEntity;
import com.example.world_of_tanks.models.dto.DeleteUserDTO;
import com.example.world_of_tanks.models.dto.EditUserDTO;
import com.example.world_of_tanks.models.dto.RegisterDTO;
import com.example.world_of_tanks.models.enums.UserRoleEnum;
import com.example.world_of_tanks.repositories.UserRepository;
import com.example.world_of_tanks.repositories.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;

    }


    public boolean register(RegisterDTO registerDTO) {

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return false;
        }

        Optional<UserEntity> userByUsername = this.userRepository.findByUsername(registerDTO.getUsername());

        if (userByUsername.isPresent()) {
            return false;
        }

        Optional<UserEntity> userByEmail = this.userRepository.findByEmail(registerDTO.getEmail());

        if (userByEmail.isPresent()) {
            return false;
        }

        UserRoleEnum userRoleEnum = registerDTO.getRole();

        UserRoleEntity role = this.userRoleRepository.findByUserRole(userRoleEnum);

        UserEntity user = new UserEntity().setEmail(registerDTO.getEmail()).setRoles(List.of(role))
                .setUsername(registerDTO.getUsername()).setPassword(passwordEncoder.encode(registerDTO.getPassword()))
                .setFullName(registerDTO.getFullName());

        this.userRepository.save(user);

        return true;
    }


    public boolean editUser(EditUserDTO editUserDTO) {

        Optional<UserEntity> user = this.userRepository.findByUsername(editUserDTO.getOldUsername());

        if (user.isEmpty()) {
            return false;
        }

        UserEntity entityToEdit = user.get();

        entityToEdit.setFullName(editUserDTO.getFullName()).setPassword(passwordEncoder.encode(editUserDTO.getPassword()))
                .setUsername(editUserDTO.getNewUsername());

        this.userRepository.save(entityToEdit);

        return true;

    }

    public boolean deleteUser(DeleteUserDTO deleteUserDTO) {

        Optional<UserEntity> user = this.userRepository.findByUsername(deleteUserDTO.getUsername());

        if (user.isEmpty()) {
            return false;
        }

        this.userRepository.delete(user.get());

        return true;

    }
}

