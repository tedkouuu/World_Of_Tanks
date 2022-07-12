package com.example.world_of_tanks.services;

import com.example.world_of_tanks.models.Category;
import com.example.world_of_tanks.models.UserRoleEntity;
import com.example.world_of_tanks.models.enums.CategoryEnum;
import com.example.world_of_tanks.models.enums.UserRoleEnum;
import com.example.world_of_tanks.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void seedUserRoles() {

        if (userRoleRepository.count() != 0) {
            return;
        }

        UserRoleEnum[] roleEnums = UserRoleEnum.values();

        for (UserRoleEnum roleEnum : roleEnums) {

            UserRoleEntity userRole = new UserRoleEntity().setUserRole(roleEnum);

            this.userRoleRepository.save(userRole);
        }
    }
}
